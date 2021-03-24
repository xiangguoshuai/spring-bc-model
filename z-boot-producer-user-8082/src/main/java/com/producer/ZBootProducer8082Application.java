package com.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ZBootProducer8082Application {

    public static void main(String[] args) {
        SpringApplication.run(ZBootProducer8082Application.class, args);
        System.out.println("服务提供者启动成功==============>>");
    }

}
