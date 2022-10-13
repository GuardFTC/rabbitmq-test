package com.ftc.workmode.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 10:47:47
 * @describe: 工作模式组件配置
 */
@Configuration
public class WorkModeConfig {

    @Bean
    public Queue workModeQueue() {
        return new Queue("work-mode-queue", false);
    }
}
