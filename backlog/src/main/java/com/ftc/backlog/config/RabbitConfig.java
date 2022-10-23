package com.ftc.backlog.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-22 16:16:54
 * @describe: RabbitMq组件配置
 */
@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("dead-letter-exchange")
                .durable(false)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder
                .nonDurable("dead-letter-queue")
                .build();
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(fanoutExchange());
    }

    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder
                .directExchange("backlog-exchange")
                .durable(false)
                .build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder
                .nonDurable("backlog-queue")
                .ttl(10000)
                .deadLetterExchange("dead-letter-exchange")
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with("backlog-queue");
    }
}
