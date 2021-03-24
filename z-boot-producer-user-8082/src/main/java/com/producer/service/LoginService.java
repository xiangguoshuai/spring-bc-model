package com.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.producer.helper.RedisLockHelper;
import com.producer.mq.SendService;

import lombok.extern.slf4j.Slf4j;
import user.client.dto.UserDto;

@Slf4j
@RestController
public class LoginService{

	private static final int TIMEOUT = 5*1000;
	
	@Autowired
	SendService sendService;
	@Autowired
	RedisLockHelper redisLockHelper;

	@RequestMapping("login")
	public UserDto login(@RequestBody UserDto userDto) {
		
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		userDto.setToken(token);
		
		String msg = JSON.toJSONString(userDto);
		log.info("登录成功==========>>"+userDto.toString());
		if(null == userDto.getOrderId()) {
			return userDto;
		}
		//分布式加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLockHelper.lock(userDto.getOrderId(),String.valueOf(time))){
            log.info("获取锁失败，请稍后再试.");
            userDto.setMsg("获取锁失败，请稍后再试.");
            return userDto;
        }
        
        //此处省略业务代码..........
        
        //分布式解锁
        redisLockHelper.unlock(userDto.getOrderId(),String.valueOf(time));
		
		
		//分布式事务
		sendService.send(msg,token);
	
		return userDto;
	}

	


}
