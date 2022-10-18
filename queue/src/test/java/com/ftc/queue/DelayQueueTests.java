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
class DelayQueueTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDelayQueueTtl() {
        rabbitTemplate.convertAndSend("normal-exchange", "ttl-queue", "延迟队列-队列TTL消息");
        StaticLog.info("延迟队列-队列TTL消息发送成功");
    }

    @Test
    void testDelayMessageTtl() {

        //1.发送第一条消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("4000");
        Message message = new Message("延迟队列-消息TTL 第一条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("normal-exchange", "ttl-queue", message);
        StaticLog.info("延迟队列-消息TTL 第一条消息发送成功");

        //2.发送第二条消息
        messageProperties = new MessageProperties();
        messageProperties.setExpiration("1000");
        message = new Message("延迟队列-消息TTL 第二条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("normal-exchange", "ttl-queue", message);
        StaticLog.info("延迟队列-消息TTL 第二条消息发送成功");
    }

    @Test
    void testDelayMessagePlugin() {

        //1.发送第一条消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(10000);
        Message message = new Message("延迟队列-插件 第一条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("delay-exchange", "delay-queue", message);
        StaticLog.info("延迟队列-插件 第一条消息发送成功");

        //2.发送第二条消息
        messageProperties = new MessageProperties();
        messageProperties.setDelay(1000);
        message = new Message("延迟队列-插件 第二条消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("delay-exchange", "delay-queue", message);
        StaticLog.info("延迟队列-插件 第二条消息发送成功");
    }
}
