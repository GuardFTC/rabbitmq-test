package com.ftc.rabbitmqmulti.config;

import cn.hutool.log.StaticLog;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitModuleConfigTest {

    @Resource
    @Qualifier("primaryRabbitTemplate")
    private RabbitTemplate primaryTemplate;

    @Resource
    @Qualifier("secondaryRabbitTemplate")
    private RabbitTemplate secondaryTemplate;

    @Test
    void testSend() {

        //1.主数据源发送消息
        primaryTemplate.convertAndSend("primary-exchange", "primary-queue", "主数据源消息");
        StaticLog.info("Primary send message success");

        //2.从数据源发送消息
        secondaryTemplate.convertAndSend("secondary-exchange", "secondary-queue", "从数据源消息");
        StaticLog.info("Secondary send message success");
    }
}