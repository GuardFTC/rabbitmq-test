package com.ftc.queue;

import cn.hutool.log.StaticLog;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class QueueApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDeadLetterQueueTtl() {
        rabbitTemplate.convertAndSend("normal-exchange", "ttl-queue", "TTL队列消息");
        StaticLog.info("TTL队列消息发送成功");
    }

    @Test
    @SneakyThrows(InterruptedException.class)
    void testDeadLetterQueueMaxLength() {
        for (int i = 1; i <= 4; i++) {
            rabbitTemplate.convertAndSend("normal-exchange", "max-length-queue", "第" + i + "条MAX_LENGTH队列消息");
            StaticLog.info("MAX_LENGTH队列第" + i + "条消息发送成功");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    @Test
    void testDeadLetterQueueUnAckOrReject() {

        //1.发送消费异常消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("index", 1);
        Message message = new Message("消费异常消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("normal-exchange", "unack-or-reject-queue", message);
        StaticLog.info("消费异常消息发送成功");

        //2.发送被拒绝消息
        messageProperties = new MessageProperties();
        messageProperties.setHeader("index", 2);
        message = new Message("被拒绝消息".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.send("normal-exchange", "unack-or-reject-queue", message);
        StaticLog.info("被拒绝消息发送成功");
    }
}
