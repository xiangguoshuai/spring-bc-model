package com.eureka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ZBootEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZBootEurekaApplication.class, args);
		System.out.println("Eureka启动成功========================");
	}

}
