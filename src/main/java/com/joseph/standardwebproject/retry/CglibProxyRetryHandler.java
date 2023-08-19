package com.joseph.standardwebproject.retry;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Component
public class CglibProxyRetryHandler implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
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
                    System.out.println("cglib dynamic proxy addOrder() Won't retry, exit!");
                }
            }
        }
        return null;
    }

    public Object getCglibProxy(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }
}
