package com.ftc.ordermessage.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 17:45:59
 * @describe: RabbitMq组件配置
 */
@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange orderExchange() {
        return ExchangeBuilder.directExchange("order-exchange").durable(false).build();
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable("order-queue").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order-queue");
    }
}
