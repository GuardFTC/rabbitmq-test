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
class PriorityQueueTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testPriorityMessage() {

        //1.发送第一条消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setPriority(1);
        Message message = new Message("优先级队列第一条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("priority-exchange", "priority-queue", message);
        StaticLog.info("优先级队列第一条消息发送成功");

        //2.发送第二条消息
        messageProperties = new MessageProperties();
        messageProperties.setPriority(2);
        message = new Message("优先级队列第二条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("priority-exchange", "priority-queue", message);
        StaticLog.info("优先级队列第二条消息发送成功");

        //2.发送第三条消息
        messageProperties = new MessageProperties();
        messageProperties.setPriority(3);
        message = new Message("优先级队列第三条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("priority-exchange", "priority-queue", message);
        StaticLog.info("优先级队列第三条消息发送成功");
    }
}
