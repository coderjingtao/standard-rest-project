package com.joseph.standardwebproject.transaction;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author joseph
 * @create 2023-08-20
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MultiThreadTransactionManager {

    private final PlatformTransactionManager transactionManager;

    private TransactionStatus openNewTransaction(PlatformTransactionManager transactionManager){
        //DataSourceTransactionManager根据transactionDefinition信息来进行一些连接属性的设置，包括隔离级别和传播行为
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //创建并开启一个新事务：此时autocommit已经被设置成了false
        return transactionManager.getTransaction(transactionDefinition);
    }

    /**
     * 多线程执行数据库任务，执行的是无返回值任务
     * @param tasks 异步执行的任务列表
     * @param executor 异步执行任务需要用到的线程池；考虑到线程池需要隔离，这里强制要求传入
     */
    public void runAsyncButWaitUntilAllDone(List<Runnable> tasks, Executor executor){
        if(executor == null){
            throw new IllegalArgumentException("线程池不能为空");
        }
        //线程中是否发生了异常
        AtomicBoolean hasEx = new AtomicBoolean();

        List<CompletableFuture> taskFutureList = new ArrayList<>(tasks.size());
        List<TransactionStatus> transactionStatusList = new ArrayList<>(tasks.size());
        List<TransactionResource> transactionResources = new ArrayList<>(tasks.size());

        tasks.forEach( task -> {
            taskFutureList.add(CompletableFuture.runAsync(()->{
                try{
                    //1.开启新事务
                    log.info("开启新事务");
                    transactionStatusList.add(openNewTransaction(transactionManager));
                    //2.拷贝事务资源
                    transactionResources.add(TransactionResource.copyTransactionResource());
                    //3.异步任务执行
                    task.run();
                }catch (Throwable throwable){
                    //1.打印异常
                    throwable.printStackTrace();
                    //2.如果异步任务出现异常进行标记
                    hasEx.set(Boolean.TRUE);
                    //3.出现异常后，其他任务还没有执行的不需要执行了
                    taskFutureList.forEach(completableFuture -> completableFuture.cancel(true));
                }
            },executor));
        });

        try {
            //阻塞直到所有任务都全部执行结束：如果有任务被取消，这里会抛出异常
            CompletableFuture.allOf(taskFutureList.toArray(new CompletableFuture[]{})).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //发生异常则进行回滚操作
        if(hasEx.get()){
            System.out.println("发生异常，全部事务回滚");
            for (int i = 0; i < tasks.size(); i++) {
                transactionResources.get(i).autoWiredTransactionResource();
                transactionManager.rollback(transactionStatusList.get(i));
                transactionResources.get(i).removeTransactionResource();
            }
        }else{
            System.out.println("全部事务正常提交");
            for (int i = 0; i < tasks.size(); i++) {
                transactionResources.get(i).autoWiredTransactionResource();
                transactionManager.commit(transactionStatusList.get(i));
                transactionResources.get(i).removeTransactionResource();
            }
        }
    }

    @Builder
    private static class TransactionResource{
        private Map<Object,Object> resources = new HashMap<>();

        private Set<TransactionSynchronization> synchronizations = new HashSet<>();
        private String currentTransactionName;
        private Boolean currentTransactionReadOnly;
        private Integer currentTransactionIsolationLevel;
        private Boolean actualTransactionActive;

        /**
         * 将事务资源在两个线程（thread-cache-pool-1 和 main）间来回复制
         * @return
         */
        public static TransactionResource copyTransactionResource(){
            return TransactionResource.builder()
                    .resources(TransactionSynchronizationManager.getResourceMap())
                    .synchronizations(new LinkedHashSet<>())
                    .currentTransactionName(TransactionSynchronizationManager.getCurrentTransactionName())
                    .currentTransactionReadOnly(TransactionSynchronizationManager.isCurrentTransactionReadOnly())
                    .currentTransactionIsolationLevel(TransactionSynchronizationManager.getCurrentTransactionIsolationLevel())
                    .actualTransactionActive(TransactionSynchronizationManager.isActualTransactionActive())
                    .build();
        }

        public void autoWiredTransactionResource(){
            resources.forEach(TransactionSynchronizationManager::bindResource);
            TransactionSynchronizationManager.initSynchronization();
            TransactionSynchronizationManager.setActualTransactionActive(actualTransactionActive);
            TransactionSynchronizationManager.setCurrentTransactionName(currentTransactionName);
            TransactionSynchronizationManager.setCurrentTransactionIsolationLevel(currentTransactionIsolationLevel);
            TransactionSynchronizationManager.setCurrentTransactionReadOnly(currentTransactionReadOnly);
        }

        public void removeTransactionResource(){
            resources.keySet().forEach(TransactionSynchronizationManager::unbindResourceIfPossible);
        }
    }
}
