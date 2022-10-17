package com.ftc.consumerack.consumer;

import cn.hutool.log.StaticLog;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-17 09:38:28
 * @describe: 消费者
 */
@Component
public class TestConsumer {

    /**
     * Ack确认消息Index
     */
    private static final Integer ACK_INDEX = 1;

    /**
     * UnAck确认消息Index
     */
    private static final Integer REJECT_INDEX = 2;

    /**
     * Reject确认消息Index
     */
    private static final Integer UN_ACK_INDEX = 3;

    @SneakyThrows
    @RabbitListener(queues = {"ack-queue"})
    public void consumer(Message message, Channel channel) {

        //1.获取消息配置
        Integer index = message.getMessageProperties().getHeader("index");

        //2.不同index进行不同的确认
        if (ACK_INDEX.equals(index)) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else if (REJECT_INDEX.equals(index)) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } else if (UN_ACK_INDEX.equals(index)) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

        //3.消费消息进行控制台打印
        StaticLog.info("Ack receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
