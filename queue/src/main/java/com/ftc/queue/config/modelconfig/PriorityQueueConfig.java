package com.ftc.queue.config.modelconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 09:40:28
 * @describe: 优先级队列组件配置
 */
@Configuration
public class PriorityQueueConfig {

    @Bean
    public DirectExchange priorityExchange() {
        return ExchangeBuilder.directExchange("priority-exchange")
                .durable(false)
                .build();
    }

    @Bean
    public Queue priorityQueue() {
        return QueueBuilder.durable("priority-queue")
                .maxPriority(50)
                .build();
    }

    @Bean
    public Binding priorityBinding() {
        return BindingBuilder.bind(priorityQueue())
                .to(priorityExchange())
                .with("priority-queue");
    }
}
