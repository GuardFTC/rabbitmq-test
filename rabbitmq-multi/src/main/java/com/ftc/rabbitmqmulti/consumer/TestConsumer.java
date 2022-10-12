package com.ftc.rabbitmqmulti.consumer;

import cn.hutool.log.StaticLog;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 15:43:39
 * @describe: 测试消费者
 */
@Component
public class TestConsumer {

    @RabbitListener(queues = {"primary-queue"}, containerFactory = "primaryListenerFactory")
    public void primaryConsumer(String message) {
        StaticLog.info("Primary consumer receive message:[{}]", message);
    }

    @SneakyThrows
    @RabbitListener(queues = {"secondary-queue"}, containerFactory = "secondaryListenerFactory")
    public void secondaryConsumer(Channel channel, Message message) {
        StaticLog.info("Secondary consumer receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
