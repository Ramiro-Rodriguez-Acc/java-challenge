package com.gateway.loadbalancer;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier staticServiceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {
            @Override
            public String getServiceId() {
                return "service";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.just(Arrays.asList(
                        new DefaultServiceInstance("java-app-1-1", "service", "java-app-1", 8080, false),
                        new DefaultServiceInstance("java-app-2-1", "service", "java-app-2", 8080, false),
                        new DefaultServiceInstance("java-app-3-1", "service", "java-app-3", 8080, false)
                ));
            }
        };
    }
}
