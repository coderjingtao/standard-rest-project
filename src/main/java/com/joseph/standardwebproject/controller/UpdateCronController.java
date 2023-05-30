package com.joseph.standardwebproject.controller;

import com.joseph.standardwebproject.common.response.CommonResponse;
import com.joseph.standardwebproject.job.DynamicScheduledJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UpdateCronController {

    private final DynamicScheduledJob dynamicScheduledJob;

    /**
     * e.g. http://localhost:8080/updateCron?cron=0/15 * * * * ?
     * @param cron
     * @return
     */
    @GetMapping("/updateCron")
    public CommonResponse<String> updateCron(String cron){
        log.info("new cron = {}", cron);
        dynamicScheduledJob.setCron(cron);
        return new CommonResponse<>("success");
    }
}
