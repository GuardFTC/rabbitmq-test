package com.ftc.workmode.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: 路由模式组件配置
 */
@Configuration
public class RouteModeConfig {

    @Bean
    public DirectExchange routeModeExchange() {
        return new DirectExchange("route-mode-exchange", false, false);
    }

    @Bean
    public Queue routeModeQueue1() {
        return new Queue("route-mode-queue1", false);
    }

    @Bean
    public Queue routeModeQueue2() {
        return new Queue("route-mode-queue2", false);
    }

    @Bean
    public Queue routeModeQueue3() {
        return new Queue("route-mode-queue3", false);
    }

    @Bean
    public Binding routeBinding1() {
        return BindingBuilder.bind(routeModeQueue1())
                .to(routeModeExchange())
                .with("route-mode-queue1");
    }

    @Bean
    public Binding routeBinding2() {
        return BindingBuilder.bind(routeModeQueue2())
                .to(routeModeExchange())
                .with("route-mode-queue2");
    }

    @Bean
    public Binding routeBinding3() {
        return BindingBuilder.bind(routeModeQueue3())
                .to(routeModeExchange())
                .with("route-mode-queue3");
    }
}
