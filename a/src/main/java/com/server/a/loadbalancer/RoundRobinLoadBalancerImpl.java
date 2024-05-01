package com.server.a.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoundRobinLoadBalancerImpl implements Loadbalancer{

    private static final ConcurrentHashMap<String,Integer> serviceIdLBMap = new ConcurrentHashMap<>();

    public RoundRobinLoadBalancerImpl() {
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> services, String serviceId) {

        if(!serviceIdLBMap.containsKey(serviceId)){
            serviceIdLBMap.put(serviceId,0);
        }

        ServiceInstance serviceInstance;

        synchronized (serviceIdLBMap){
            int instanceNum = serviceIdLBMap.get(serviceId);
            if(instanceNum > services.size() -1){
                instanceNum = 0;
            }
            serviceInstance = services.get(instanceNum);
            ++instanceNum;
            serviceIdLBMap.put(serviceId,instanceNum);
            return serviceInstance;
        }

    }
}
