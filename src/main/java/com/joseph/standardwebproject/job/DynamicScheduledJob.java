package com.joseph.standardwebproject.job;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;

@Configuration
@EnableScheduling
@Slf4j
@PropertySource("classpath:/task-config.ini")
public class DynamicScheduledJob implements SchedulingConfigurer {

    @Value("${syncSalesOrder.cron}")
    @Setter
    private String cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                log.info("SyncSalesOrder Job start... cron = {}",cron);
            }
        }, new Trigger() {
            @Override
            public Instant nextExecution(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(cron);
                return cronTrigger.nextExecution(triggerContext);
            }
        });
    }
}
