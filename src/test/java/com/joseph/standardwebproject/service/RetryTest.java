package com.joseph.standardwebproject.service;

import com.joseph.standardwebproject.retry.ApiService;
import com.joseph.standardwebproject.retry.CglibProxyRetryHandler;
import com.joseph.standardwebproject.retry.RetryInvocationHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author joseph
 * @create 2023-08-18
 */
@SpringBootTest
public class RetryTest{

    @Autowired
    ApiService apiService;
    @Autowired
    ApiService apiProxyService;
    @Autowired
    CglibProxyRetryHandler cglibProxyRetryHandler;
    @Autowired
    ApiService apiServiceImplForAop;

    @Test
    public void testRetry1() throws Exception {
        apiService.addOrder();
    }

    @Test
    public void testRetry2() throws Exception {
        apiProxyService.addOrder();
    }

    @Test
    public void testRetry3() throws Exception {
        ApiService apiServiceProxy = (ApiService) RetryInvocationHandler.getProxy(apiService);
        apiServiceProxy.addOrder();
    }

    @Test
    public void testRetry4() throws Exception {
        ApiService apiServiceProxy = (ApiService) cglibProxyRetryHandler.getCglibProxy(apiService);
        apiServiceProxy.addOrder();
    }

    @Test
    public void testRetry5() throws Exception {
        apiServiceImplForAop.addOrder();
    }
}
