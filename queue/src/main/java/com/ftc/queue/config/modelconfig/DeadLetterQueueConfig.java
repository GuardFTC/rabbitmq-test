package com.ftc.queue.config.modelconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 09:40:28
 * @describe: 死信队列组件配置
 */
@Configuration
public class DeadLetterQueueConfig {

    /**
     * 死信交换机
     */
    public static final String DEAD_LETTER_EXCHANGE = "dead-letter-exchange";

    /**
     * 死信交换机
     */
    public static final String DEAD_LETTER_QUEUE = "dead-letter-queue";

    /**
     * 死信绑定Key
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "dead-letter-key";

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, false, false);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, false);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange("normal-exchange", false, false);
    }

    @Bean
    public Queue ttlQueue() {
        return QueueBuilder.durable("ttl-queue")
                .ttl(5000)
                .deadLetterExchange(DEAD_LETTER_EXCHANGE)
                .deadLetterRoutingKey(DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue maxLengthQueue() {
        return QueueBuilder.durable("max-length-queue")
                .maxLength(2)
                .deadLetterExchange(DEAD_LETTER_EXCHANGE)
                .deadLetterRoutingKey(DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue unAckOrRejectQueue() {
        return QueueBuilder.durable("unack-or-reject-queue")
                .deadLetterExchange(DEAD_LETTER_EXCHANGE)
                .deadLetterRoutingKey(DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(ttlQueue())
                .to(normalExchange())
                .with("ttl-queue");
    }

    @Bean
    public Binding maxLengthBinding() {
        return BindingBuilder.bind(maxLengthQueue())
                .to(normalExchange())
                .with("max-length-queue");
    }

    @Bean
    public Binding unAckOrRejectBinding() {
        return BindingBuilder.bind(unAckOrRejectQueue())
                .to(normalExchange())
                .with("unack-or-reject-queue");
    }
}
