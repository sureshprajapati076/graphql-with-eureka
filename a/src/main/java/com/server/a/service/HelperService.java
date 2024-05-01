package com.server.a.service;

import com.server.a.dto.ResponseB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HelperService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    public ResponseB helper(Map<String,String> reqMap){
        String url = buildUrl("SERVICE-B","/service/b/post");
        HttpEntity<Map<String,String>> httpEntity = new HttpEntity<>(reqMap);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("serviceb");
        return circuitBreaker.run(() -> {
                    var response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseB.class);

                    System.out.println(response.getBody());
                    return response.getBody();
                },
                this::fallBackMethod);


    }

    public void helperResetCounter(){
        String url = buildUrl("SERVICE-B","/service/b/reset");

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("serviceb");
        circuitBreaker.run(() -> {
                    var response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

                    System.out.println(response.getBody());
                    return response.getBody();
                },
                this::fallBackMethod);


    }

    private ResponseB fallBackMethod(Throwable throwable) {
        throw new RuntimeException("circuit breaker executed.");
    }

    private String buildUrl(String serviceName, String uri) {

        var listOfInstances = discoveryClient.getInstances(serviceName);

        return listOfInstances.get(0).getUri().toString()+uri;
    }


}
