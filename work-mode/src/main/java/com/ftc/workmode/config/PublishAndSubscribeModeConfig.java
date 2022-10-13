package com.ftc.workmode.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: 发布订阅模式组件配置
 */
@Configuration
public class PublishAndSubscribeModeConfig {

    @Bean
    public FanoutExchange publishSubscribeExchange() {
        return new FanoutExchange("publish-subscribe-exchange", false, false);
    }

    @Bean
    public Queue publishSubscribeQueue1() {
        return new Queue("publish-subscribe-queue1", false);
    }

    @Bean
    public Queue publishSubscribeQueue2() {
        return new Queue("publish-subscribe-queue2", false);
    }

    @Bean
    public Queue publishSubscribeQueue3() {
        return new Queue("publish-subscribe-queue3", false);
    }

    @Bean
    public Binding publishSubscribeBinding1() {
        return BindingBuilder.bind(publishSubscribeQueue1()).to(publishSubscribeExchange());
    }

    @Bean
    public Binding publishSubscribeBinding2() {
        return BindingBuilder.bind(publishSubscribeQueue2()).to(publishSubscribeExchange());
    }

    @Bean
    public Binding publishSubscribeBinding3() {
        return BindingBuilder.bind(publishSubscribeQueue3()).to(publishSubscribeExchange());
    }
}
