package com.server.a.loadbalancer;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryProperties;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadBalancerDiscoveryClient extends EurekaDiscoveryClient {

    Loadbalancer lb;

    SimpleDiscoveryProperties simpleDiscoveryProperties;
    public LoadBalancerDiscoveryClient(EurekaClient eurekaClient, EurekaClientConfig clientConfig, Loadbalancer loadbalancer, SimpleDiscoveryProperties simpleDiscoveryProperties) {
        super(eurekaClient, clientConfig);
        this.lb = loadbalancer;
        this.simpleDiscoveryProperties = simpleDiscoveryProperties;
    }

    public List<ServiceInstance> getInstances(String serviceId){
        List<ServiceInstance> respectiveServiceInstances = new ArrayList<>();
        List<ServiceInstance> serviceInstances = !this.simpleDiscoveryProperties.getInstances().containsKey(serviceId) ? super.getInstances(serviceId) : null;
        if(!ObjectUtils.isEmpty(serviceInstances)){
            ServiceInstance serviceInstance = this.lb.getServiceInstance(serviceInstances,serviceId);
            respectiveServiceInstances.add(serviceInstance);
        }
        return respectiveServiceInstances;
    }

}