package com.ftc.backlog;

import cn.hutool.log.StaticLog;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-22 16:20:01
 * @describe: 消费者
 */
@Component
public class TestConsumer {

    @SneakyThrows(InterruptedException.class)
    @RabbitListener(queues = {"backlog-queue"})
    public void consumer1(String message) {

        //1.先睡1000ms钟，模拟Consumer消费效率低
        TimeUnit.MILLISECONDS.sleep(1000);

        //2.控制台打印消息内容，模拟正常业务流程
        StaticLog.info("Consumer1 receive message:[{}]", message);
    }

    @SneakyThrows(InterruptedException.class)
    @RabbitListener(queues = {"backlog-queue"})
    public void consumer2(String message) {

        //1.先睡500ms钟，模拟Consumer消费效率低
        TimeUnit.MILLISECONDS.sleep(500);

        //2.控制台打印消息内容，模拟正常业务流程
        StaticLog.info("Consumer2 receive message:[{}]", message);
    }

    @SneakyThrows(InterruptedException.class)
    @RabbitListener(queues = {"backlog-queue"})
    public void consumer3(String message) {

        //1.先睡100ms钟，模拟Consumer消费效率低
        TimeUnit.MILLISECONDS.sleep(100);

        //2.控制台打印消息内容，模拟正常业务流程
        StaticLog.info("Consumer3 receive message:[{}]", message);
    }

    @RabbitListener(queues = {"dead-letter-queue"})
    public void deadLetterQueueConsumer(String message) {
        StaticLog.info("DeadLetterQueueConsumer receive message:[{}]", message);
    }
}
