package com.ftc.notrepeat;

import com.ftc.notrepeat.util.RabbitMqUtil;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class NotRepeatApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSend() {

        //1.定义参数
        String exchange = "not-repeat-exchange";
        String routingKey = "not-repeat-queue";
        String messageContent = "测试消息不丢";

        //2.创建消息
        Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8));
        CorrelationData correlationData = RabbitMqUtil.getCorrelationData(exchange, routingKey, message);
        message = RabbitMqUtil.setMessageIdToHeader(message, correlationData.getId());

        //3.发送消息
        rabbitTemplate.send(exchange, routingKey, message, correlationData);
    }
}
