package com.ftc.queue;

import cn.hutool.log.StaticLog;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class LazyQueueTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testPriorityMessage() {


    }
}
