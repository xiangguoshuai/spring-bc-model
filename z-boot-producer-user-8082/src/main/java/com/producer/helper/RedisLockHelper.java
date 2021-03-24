package com.producer.helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisLockHelper {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 加锁
	 * 
	 * @param targetId  唯一标志
	 * @param timeStamp 当前时间+超时时间 也就是时间戳
	 * @return
	 */
	public Boolean lock(String targetId, String timeStamp) {
		if (stringRedisTemplate.opsForValue().setIfAbsent(targetId, timeStamp)) {
			return true;
		}
		String currentLock = stringRedisTemplate.opsForValue().get(targetId);
		if (!StringUtils.isNotEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()) {
			String preLock = stringRedisTemplate.opsForValue().getAndSet(targetId, timeStamp);
			//获取上一个锁的时间 如果高并发的情况可能会出现已经被修改的问题  所以多一次判断保证线程的安全
			if (!StringUtils.isNotEmpty(preLock) && preLock.equals(currentLock)) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * 解锁
     * @param target
     * @param timeStamp
     */
    public void unlock(String target,String timeStamp){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(target);
            if(StringUtils.isNotEmpty(currentValue) && currentValue.equals(timeStamp) ){
                stringRedisTemplate.opsForValue().getOperations().delete(target);
            }
        } catch (Exception e) {
            log.error("警报！警报！解锁异常{}",e);
        }
    }

}
