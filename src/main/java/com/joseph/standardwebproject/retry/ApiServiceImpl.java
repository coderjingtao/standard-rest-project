package com.joseph.standardwebproject.retry;

import org.springframework.stereotype.Service;

/**
 * @author joseph
 * @create 2023-08-18
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService{

    @Override
    public void addOrder() throws Exception {
        int times = 1;
        while(times <= 5){
            try{
                //invoke the api of adding order
                int i = 3 / 0;
            }catch (Exception e){
                System.out.println("retry "+ times + " times");
                Thread.sleep(2000);
                times++;
                if(times > 5){
                    System.out.println("original addOrder() Won't retry, exit!");
                }
            }
        }
    }
}
