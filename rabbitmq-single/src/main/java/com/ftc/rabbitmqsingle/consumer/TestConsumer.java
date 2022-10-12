package com.ftc.rabbitmqsingle.consumer;

import cn.hutool.log.StaticLog;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 11:05:35
 * @describe: 默认测试消费者
 */
@Component
public class TestConsumer {

    @RabbitListener(queues = {"default-queue"})
    public void getMessage(String message) {
        StaticLog.info("Consumer receive message:{}", message);
    }
}
