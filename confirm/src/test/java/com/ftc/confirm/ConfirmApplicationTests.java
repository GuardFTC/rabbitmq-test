package com.ftc.confirm;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfirmApplicationTests {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    void testConfirmCallback() {
        rabbitTemplate.convertAndSend("undefined", "confirm-queue", "测试Confirm消息");
    }

    @Test
    void testReturnCallback() {
        rabbitTemplate.convertAndSend("confirm-exchange", "undefined", "测试Return消息");
    }

    @Test
    void testOk() {
        rabbitTemplate.convertAndSend("confirm-exchange", "confirm-queue", "测试Return消息");
    }
}
