package com.ftc.queue.config.modelconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 09:40:28
 * @describe: 惰性队列队列组件配置
 */
@Configuration
public class LazyQueueConfig {

    @Bean
    public DirectExchange lazyExchange() {
        return ExchangeBuilder.directExchange("lazy-exchange")
                .durable(false)
                .build();
    }

    @Bean
    public Queue lazyQueue() {
        return QueueBuilder.durable("lazy-queue")
                .lazy()
                .build();
    }

    @Bean
    public Binding lazyBinding() {
        return BindingBuilder.bind(lazyQueue())
                .to(lazyExchange())
                .with("lazy-queue");
    }
}
