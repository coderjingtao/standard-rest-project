package com.joseph.standardwebproject.retry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author joseph
 * @create 2023-08-18
 */
public class RetryInvocationHandler implements InvocationHandler {
    private final Object target;
    public RetryInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int times = 1;
        while(times <= 5){
            try{
                int i = 3 / 0;
                //invoke the method of target
                return method.invoke(target,args);
            }catch (Exception e){
                System.out.println("retry "+ times + " times");
                Thread.sleep(2000);
                times++;
                if(times > 5){
                    System.out.println("dynamic proxy addOrder() Won't retry, exit!");
                }
            }
        }
        return null;
    }

    public static Object getProxy(Object target){
        InvocationHandler handler = new RetryInvocationHandler(target);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(), target.getClass().getInterfaces(),handler);
    }
}
