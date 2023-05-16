package com.joseph.standardwebproject.common.utils;

import org.springframework.web.client.RestTemplate;

public class MyRestTemplate extends MyLombokRestOperation{

    private RestTemplate restTemplate;

    protected MyRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
        this.restTemplate = restTemplate;
    }



}
