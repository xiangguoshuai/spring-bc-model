package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ZBootConsumerApplication {

//	@LoadBalanced
//	@Bean
//    RestTemplate getRestTemplate(){
//		
//        return new RestTemplate();
//    }

	
    public static void main(String[] args) {
        SpringApplication.run(ZBootConsumerApplication.class, args);
        System.out.println("消费者启动成功=============================");
    }

}
