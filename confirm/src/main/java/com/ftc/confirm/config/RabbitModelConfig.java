package com.ftc.confirm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-17 09:35:05
 * @describe: RabbitMq组件配置
 */
@Configuration
public class RabbitModelConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("confirm-exchange", false, false);
    }

    @Bean
    public Queue directQueue() {
        return new Queue("confirm-queue", false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with("confirm-queue");
    }
}
