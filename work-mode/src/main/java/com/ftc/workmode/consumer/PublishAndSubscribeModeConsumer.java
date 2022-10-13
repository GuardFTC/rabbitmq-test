package com.ftc.workmode.consumer;

import cn.hutool.log.StaticLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 18:32:23
 * @describe: 发布订阅模式消费者
 */
@Component
public class PublishAndSubscribeModeConsumer {

    @RabbitListener(queues = {"publish-subscribe-queue1"})
    public void consumer1(Message message) {
        StaticLog.info("PublishAndSubscribeModeConsumer1 receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }

    @RabbitListener(queues = {"publish-subscribe-queue2"})
    public void consumer2(Message message) {
        StaticLog.info("PublishAndSubscribeModeConsumer2 receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }

    @RabbitListener(queues = {"publish-subscribe-queue3"})
    public void consumer3(Message message) {
        StaticLog.info("PublishAndSubscribeModeConsumer3 receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
