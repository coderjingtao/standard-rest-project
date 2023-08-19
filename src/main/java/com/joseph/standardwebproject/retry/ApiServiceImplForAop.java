package com.joseph.standardwebproject.retry;

import org.springframework.stereotype.Service;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Service("apiServiceImplForAop")
public class ApiServiceImplForAop implements ApiService{
    @Override
    @MyRetryable(retryTimes = 5, retryInterval = 2)
    public void addOrder() throws Exception {
        int i = 3 / 0;
    }
}
