package com.ftc.consumerack;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class ConsumerAckApplicationTests {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    void testConfirmCallback() {

        for (int i = 1; i <= 3; i++) {

            //1.设置消息属性
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("index", i);

            //2.创建消息
            Message message = new Message(("测试Ack消息" + i).getBytes(StandardCharsets.UTF_8), messageProperties);

            //3.发送消息
            rabbitTemplate.send("ack-exchange", "ack-queue", message);
        }
    }
}
