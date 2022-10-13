package com.ftc.workmode.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: 主题模式组件配置
 */
@Configuration
public class TopicModeConfig {

    @Bean
    public TopicExchange topicModeExchange() {
        return new TopicExchange("topic-mode-exchange", false, false);
    }

    @Bean
    public Queue topicModeQueue1() {
        return new Queue("topic-mode-queue1", false);
    }

    @Bean
    public Queue topicModeQueue2() {
        return new Queue("topic-mode-queue2", false);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicModeQueue1())
                .to(topicModeExchange())
                .with("topic.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicModeQueue2())
                .to(topicModeExchange())
                .with("topic.#");
    }
}
