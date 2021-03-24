package com.consumer.feignInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.consumer.config.IruleConfig;

import user.client.dto.UserDto;

@FeignClient(name = "boot-producer-user", configuration = IruleConfig.class)
public interface HelloFeignInterFace {
	
	@GetMapping("/login")
	public UserDto login(@RequestBody UserDto userDto);
}
