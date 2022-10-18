package com.ftc.ordermessage;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderMessageApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testOrder() {

        //1.发送数字类型的顺序消息
        for (int i = 1; i <= 10; i++) {
            rabbitTemplate.convertAndSend("order-exchange", "order-queue", i + "");
        }
//
//        //3.发送字母类型的顺序消息
//        for (int i = 0; i < 26; i++) {
//            rabbitTemplate.convertAndSend("order-exchange", "order-queue", (char) (97 + i) + "");
//        }
    }
}
