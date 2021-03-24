package com.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
	private String queueName = "UserQueue";			//队列名
	private String exchangeName = "UserExchange";	//交换机名
	private String routingName = "UserRouting";		//绑定队列和交换机
	
    @Bean
    public Queue TestDirectQueue() {
        return new Queue(queueName, true);
    }
 
    @Bean
    public DirectExchange TestDirectExchange() {
        return new DirectExchange(exchangeName,true,false);
    }
 
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(routingName);
    }

    @Bean
    public DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
}
