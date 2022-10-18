package com.ftc.queue.config.consumer;

import cn.hutool.log.StaticLog;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 10:09:27
 * @describe: 延迟队列相关消费者
 */
@Component
public class DelayQueueConsumer {

    @SneakyThrows
    @RabbitListener(queues = {"delay-queue"})
    private void deadLetterConsumer(Message message, Channel channel) {

        //1.打印消息
        StaticLog.info("DelayConsumer receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));

        //2.手动确认消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
