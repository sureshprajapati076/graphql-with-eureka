package com.server.a.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface Loadbalancer {
    ServiceInstance getServiceInstance(List<ServiceInstance> services, String instanceId);
}
