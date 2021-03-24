package com.producer.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitListenerService {
	
	@RabbitListener(queues = "UserQueue")
	@RabbitHandler
    public void process(Message message,Channel channel) throws Exception {
		byte[]  msg = message.getBody();
		String messageId = message.getMessageProperties().getMessageId();
        log.info("UserQueue消费者收到消息---->>" + new String(msg));
        log.info("messageId消息ID---->>" + messageId);
     
     try {
    	 //int a = 1/0;
    	// 手动签收消息,通知mq服务器端删除该消息
    	 channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    	 log.info("手动签收成功================================》》");
	} catch (IOException e) {
		e.printStackTrace();
		// 丢弃该消息
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        log.error(" 丢弃该消息==============并对user服务做补偿==================》》");
	}
    }
}
