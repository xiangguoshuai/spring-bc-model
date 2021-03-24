package com.consumer.conterller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.service.ConsumerService;

import user.client.dto.UserDto;

@RestController
@RequestMapping("/shop")
public class HelloConterller {
	
	@Autowired
	ConsumerService consumerService;
	
	@RequestMapping("/login")
	public UserDto login(@RequestBody UserDto userDto) {
		return consumerService.getServer(userDto);
	}
}
