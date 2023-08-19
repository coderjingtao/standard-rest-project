package com.joseph.standardwebproject.retry;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Service("apiProxyService")
public class ApiProxyServiceImpl implements ApiService{

    @Resource
    private ApiService apiService;

    @Override
    public void addOrder() throws Exception {
        int times = 1;
        while(times <= 5){
            try{
                //invoke the api of adding order
                int i = 3 / 0;
                apiService.addOrder();
            }catch (Exception e){
                System.out.println("retry "+ times + " times");
                Thread.sleep(2000);
                times++;
                if(times > 5){
                    System.out.println("proxy addOrder() Won't retry, exit!");
                }
            }
        }
    }
}
