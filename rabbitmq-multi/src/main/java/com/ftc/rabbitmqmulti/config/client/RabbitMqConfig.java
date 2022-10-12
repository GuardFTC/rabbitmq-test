package com.ftc.rabbitmqmulti.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 15:05:12
 * @describe: RabbitMq配置
 */
@Configuration
public class RabbitMqConfig {

    @Bean("primaryProperties")
    @ConfigurationProperties("spring.rabbitmq.primary")
    public RabbitMqProperties primaryProperties() {
        return new RabbitMqProperties();
    }

    @Bean("secondaryProperties")
    @ConfigurationProperties("spring.rabbitmq.secondary")
    public RabbitMqProperties secondaryProperties() {
        return new RabbitMqProperties();
    }
}
