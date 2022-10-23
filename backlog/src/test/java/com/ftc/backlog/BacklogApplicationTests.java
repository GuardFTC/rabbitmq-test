package com.ftc.backlog;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BacklogApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSend() {
        for (int i = 1; i <= 1000; i++) {
            rabbitTemplate.convertAndSend("backlog-exchange", "backlog-queue", "第" + i + "条消息");
        }
    }
}
