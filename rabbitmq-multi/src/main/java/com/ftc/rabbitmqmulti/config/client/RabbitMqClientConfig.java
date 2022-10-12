package com.ftc.rabbitmqmulti.config.client;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 15:07:03
 * @describe: RabbitMq客户端Config
 */
@Configuration
@RequiredArgsConstructor
public class RabbitMqClientConfig {

    private final RabbitMqConfig rabbitMqConfig;

    @Primary
    @Bean(name = "primaryRabbitTemplate")
    public RabbitTemplate primaryRabbitTemplate() {
        return new RabbitTemplate(primaryConnectionFactory());
    }

    @Primary
    @Bean(name = "primaryListenerFactory")
    public SimpleRabbitListenerContainerFactory primaryListenerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {

        //1.创建工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.valueOf(rabbitMqConfig.primaryProperties().getAcknowledgeMode()));

        //2.设置属性并返回
        configurer.configure(factory, primaryConnectionFactory());
        return factory;
    }

    @Primary
    @Bean(name = "primaryConnectionFactory")
    public ConnectionFactory primaryConnectionFactory() {
        return getConnectionFactory(rabbitMqConfig.primaryProperties());
    }

    @Bean(name = "secondaryRabbitTemplate")
    public RabbitTemplate secondaryRabbitTemplate() {
        return new RabbitTemplate(secondaryConnectionFactory());
    }

    @Bean(name = "secondaryListenerFactory")
    public SimpleRabbitListenerContainerFactory secondaryListenerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {

        //1.创建工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.valueOf(rabbitMqConfig.secondaryProperties().getAcknowledgeMode()));

        //2.设置属性并返回
        configurer.configure(factory, secondaryConnectionFactory());
        return factory;
    }

    @Bean(name = "secondaryConnectionFactory")
    public ConnectionFactory secondaryConnectionFactory() {
        return getConnectionFactory(rabbitMqConfig.secondaryProperties());
    }

    /**
     * 创建链接工厂
     *
     * @param properties RabbitMq配置
     * @return 链接工厂
     */
    private ConnectionFactory getConnectionFactory(RabbitMqProperties properties) {

        //1.创建工厂
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        //2.设置属性
        connectionFactory.setHost(properties.getHost());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setVirtualHost(properties.getVirtualHost());

        //3.返回
        return connectionFactory;
    }
}
