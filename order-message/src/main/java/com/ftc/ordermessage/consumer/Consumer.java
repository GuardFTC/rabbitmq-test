package com.ftc.ordermessage.consumer;

import cn.hutool.log.StaticLog;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 17:49:30
 * @describe: 消费者
 */
@Component
public class Consumer {

    @RabbitListener(queues = {"order-queue"})
    public void consumer1(String message) {
        StaticLog.info("Consumer1 receive message:[{}]", message);
    }

    @RabbitListener(queues = {"order-queue"})
    @SneakyThrows(InterruptedException.class)
    public void consumer2(String message) {

        //1.模拟该消费者消费性能较低
        TimeUnit.MILLISECONDS.sleep(500);

        //2.消费消息
        StaticLog.info("Consumer2 receive message:[{}]", message);
    }
}
