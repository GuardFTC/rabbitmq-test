package com.ftc.workmode.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: RabbitMq组件配置
 */
@Configuration
public class SimpleModeConfig {

    @Bean
    public Queue simpleModeQueue() {
        return new Queue("simple-mode-queue", false);
    }
}
