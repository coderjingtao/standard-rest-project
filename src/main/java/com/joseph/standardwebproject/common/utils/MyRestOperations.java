package com.joseph.standardwebproject.common.utils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public abstract class MyRestOperations implements RestOperations {

    protected volatile RestTemplate restTemplate;

    protected MyRestOperations(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public HttpHeaders headForHeaders(String url, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public HttpHeaders headForHeaders(URI url) throws RestClientException {
        return null;
    }

    @Override
    public URI postForLocation(String url, Object request, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public URI postForLocation(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public URI postForLocation(URI url, Object request) throws RestClientException {
        return null;
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public void put(String url, Object request, Object... uriVariables) throws RestClientException {

    }

    @Override
    public void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {

    }

    @Override
    public void put(URI url, Object request) throws RestClientException {

    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T patchForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public void delete(String url, Object... uriVariables) throws RestClientException {

    }

    @Override
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {

    }

    @Override
    public void delete(URI url) throws RestClientException {

    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return null;
    }

    @Override
    public <T> T execute(String uriTemplate, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T execute(String uriTemplate, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        return null;
    }
}
