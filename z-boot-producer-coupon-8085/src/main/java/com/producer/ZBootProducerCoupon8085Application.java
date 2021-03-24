package com.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ZBootProducerCoupon8085Application {

    public static void main(String[] args) {
        SpringApplication.run(ZBootProducerCoupon8085Application.class, args);
        System.out.println("消费者优惠券服务启动成功===============>>");
    }

}
