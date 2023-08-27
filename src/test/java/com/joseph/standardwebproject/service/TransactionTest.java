package com.joseph.standardwebproject.service;

import com.joseph.standardwebproject.entity.Item;
import com.joseph.standardwebproject.entity.ItemDetail;
import com.joseph.standardwebproject.repository.ItemDetailRepository;
import com.joseph.standardwebproject.repository.ItemRepository;
import com.joseph.standardwebproject.transaction.MultiThreadTransactionManager;
import com.joseph.standardwebproject.transaction.SingleThreadTransactionManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * @author joseph
 * @create 2023-08-20
 */
@SpringBootTest
public class TransactionTest {

    @Autowired
    ItemDetailRepository itemDetailRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SingleThreadTransactionManager singleThreadTransactionManager;
    @Autowired
    MultiThreadTransactionManager multiThreadTransactionManager;

    @Test
    public void queryBoth(){
        Long id = 4L;
        Optional<ItemDetail> itemDetail = itemDetailRepository.findById(id);
        System.out.println(itemDetail);

        Optional<Item> item = itemRepository.findById(id);
        System.out.println(item);
    }

    @Test
    public void deleteBothTableInOneThread(){
        Long id = 2L;
        singleThreadTransactionManager.removeMultipleTableRecords(id);
    }

    @Test
    public void deleteBothTableInMultiThread(){
        Long id = 1L;
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> {
            itemRepository.deleteById(id);
        });
        tasks.add(() -> {
            //int i = 3/0;
            itemDetailRepository.deleteById(id);
        });

        multiThreadTransactionManager.runAsyncButWaitUntilAllDone(tasks, Executors.newCachedThreadPool());
    }
}
