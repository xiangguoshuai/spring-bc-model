package com.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZBootZuul8085Application {

    public static void main(String[] args) {
        SpringApplication.run(ZBootZuul8085Application.class, args);
        System.out.println("网关启动成功=============================");
    }

}
