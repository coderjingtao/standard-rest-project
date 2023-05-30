package com.joseph.standardwebproject.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

//@Configuration
//@EnableScheduling
@Slf4j
public class StaticScheduledJob {

    /**
     * every 10s to execute, but its own execution time is 15s, so the actual time period is 20s
     * @Scheduled launch a single thread as default
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void task1(){
      log.info("Execute Static Job1...");

        try {
            Thread.sleep(1000 * 15);// sleep 15s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  10s after the job is finished, the job will launch again
     */
    @Scheduled(fixedDelay = 1000 * 10)
    private void task2(){
        log.info("Static Job2 Start...");

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Static Job2 End.");
    }
}
