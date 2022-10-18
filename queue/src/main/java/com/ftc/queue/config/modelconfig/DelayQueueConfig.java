package com.ftc.queue.config.modelconfig;

import cn.hutool.core.map.MapUtil;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 09:40:28
 * @describe: 延迟队列组件配置
 */
@Configuration
public class DelayQueueConfig {

    @Bean
    public CustomExchange delayExchange() {

        //1.设置交换机属性，声明该自定义交换机为直连交换机
        Map<String, Object> arguments = MapUtil.newHashMap(1);
        arguments.put("x-delayed-type", "direct");

        //2.声明交换机类型为x-delayed-message
        String type = "x-delayed-message";

        //3.创建交换机并返回
        return new CustomExchange("delay-exchange", type, false, false, arguments);
    }

    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable("delay-queue").build();
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue())
                .to(delayExchange())
                .with("delay-queue")
                .noargs();
    }
}
