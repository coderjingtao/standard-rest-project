package com.joseph.standardwebproject.common.utils;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * This class equals to MyRestOperations to wrap a 3rd-party class
 */
@AllArgsConstructor
public abstract class MyLombokRestOperation implements RestOperations {

    @Delegate
    protected volatile RestTemplate restTemplate;
}
