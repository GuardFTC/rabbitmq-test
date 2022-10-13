package com.ftc.workmode.consumer;

import cn.hutool.log.StaticLog;
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

    @RabbitListener(queues = {"simple-mode-queue"})
    public void consumer(Message message) {
        StaticLog.info("SimpleModeConsumer receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
