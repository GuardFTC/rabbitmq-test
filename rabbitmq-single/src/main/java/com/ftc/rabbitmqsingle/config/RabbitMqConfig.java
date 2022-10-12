package com.ftc.rabbitmqsingle.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: RabbitMq组件配置
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("default-exchange", false, false);
    }

    @Bean
    public Queue defaultQueue() {
        return new Queue("default-queue", false);
    }

    @Bean
    public Binding defaultBind() {
        return BindingBuilder.bind(defaultQueue())
                .to(defaultExchange())
                .with("default-queue");
    }
}
