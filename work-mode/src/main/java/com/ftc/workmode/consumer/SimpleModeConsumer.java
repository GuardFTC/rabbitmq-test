package com.ftc.workmode.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 18:32:23
 * @describe: 简单模式消费者
 */
@Component
public class SimpleModeConsumer {

    @RabbitListener(queues = "default-queue")
    public void getMessage(Message message) {
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
