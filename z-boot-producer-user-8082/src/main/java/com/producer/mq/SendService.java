package com.producer.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendService implements RabbitTemplate.ConfirmCallback{
	
	
	@Autowired
    RabbitTemplate rabbitTemplate;
	String msgService = null;
	
	//发送MQ消息方法
	public void send(String msg,String token) {
		msgService = msg;
		// 封装消息,手动签收必须封装
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(token).build();
		// 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(token);
        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        log.info("发送消息mq==========>>"+message);
        rabbitTemplate.convertAndSend("UserExchange", "UserRouting", message, correlationData);
	}
	
	//MQ确认应答机制
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		String token = correlationData.getId();
		log.info("消息id:" + correlationData.getId()); 
        if (ack) {
        	log.info("MQ确认应答机制：消息发送确认成功");
        } else {
            //重试机制
            send(msgService,token); 
            log.info("MQ确认应答机制：消息发送确认失败:" + cause);
        }
	}
}
