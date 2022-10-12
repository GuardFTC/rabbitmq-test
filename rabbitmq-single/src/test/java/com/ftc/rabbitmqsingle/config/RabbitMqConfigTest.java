package com.ftc.rabbitmqsingle.config;

import cn.hutool.log.StaticLog;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqConfigTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSend() {
        rabbitTemplate.convertAndSend("default-exchange", "default-queue", "第一条消息");
        StaticLog.info("Publisher send Message success");
    }
}