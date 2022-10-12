package com.ftc.rabbitmqmulti.config;

import com.ftc.rabbitmqmulti.config.client.RabbitMqClientConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 15:26:07
 * @describe: RabbitMq组件配置
 */
@Configuration
@RequiredArgsConstructor
public class RabbitModuleConfig {

    private final RabbitMqClientConfig rabbitMqClientConfig;

    @Bean
    @SneakyThrows
    public Channel primaryBind() {

        //1.获取工厂
        ConnectionFactory connectionFactory = rabbitMqClientConfig.primaryConnectionFactory();

        //2.获取链接
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);

        //3.声明交换机
        channel.exchangeDeclare("primary-exchange", BuiltinExchangeType.DIRECT, false);

        //4.声明队列
        channel.queueDeclare("primary-queue", false, false, false, null);

        //5.声明绑定关系
        channel.queueBind("primary-queue", "primary-exchange", "primary-queue");

        //6.返回
        return channel;
    }

    @Bean
    @SneakyThrows
    public Channel secondaryBind() {

        //1.获取工厂
        ConnectionFactory connectionFactory = rabbitMqClientConfig.secondaryConnectionFactory();

        //2.获取链接
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);

        //3.声明交换机
        channel.exchangeDeclare("secondary-exchange", BuiltinExchangeType.DIRECT, false);

        //4.声明队列
        channel.queueDeclare("secondary-queue", false, false, false, null);

        //5.声明绑定关系
        channel.queueBind("secondary-queue", "secondary-exchange", "secondary-queue");

        //6.返回
        return channel;
    }
}
