package com.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.feignInterface.HelloFeignInterFace;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import user.client.dto.UserDto;

@Service
public class ConsumerService {

    @Autowired
    private HelloFeignInterFace helloFeignInterFace;
//    @Autowired
//    private RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod = "fallbackError")
    public UserDto getServer(UserDto userDto){
    	
        return helloFeignInterFace.login(userDto);
        //return restTemplate.getForObject("http://localhost:8082/login?username=xgs",UserDto.class);
    }
    
	public UserDto fallbackError(UserDto userDto) {
		userDto.setMsg("我是熔断机制，你的服务失效了");
		return userDto;
	}
}