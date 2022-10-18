package com.ftc.queue.config.consumer;

import cn.hutool.log.StaticLog;
import com.ftc.queue.config.modelconfig.DeadLetterQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 10:09:27
 * @describe: 死信队列相关消费者
 */
@Component
public class DeadLetterQueueConsumer {

    /**
     * 消费异常index
     */
    private static final Integer UN_ACK_INDEX = 1;

    /**
     * 拒绝消息index
     */
    private static final Integer REJECT_INDEX = 2;

    @SneakyThrows
    @RabbitListener(queues = {"unack-or-reject-queue"})
    private void unAckAndRejectConsumer(Message message, Channel channel) {

        //1.获取消息属性配置
        Integer index = message.getMessageProperties().getHeader("index");

        //2.index=1,消费异常;index=2,拒绝消息
        if (UN_ACK_INDEX.equals(index)) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else if (REJECT_INDEX.equals(index)) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @SneakyThrows
    @RabbitListener(queues = {DeadLetterQueueConfig.DEAD_LETTER_QUEUE})
    private void deadLetterConsumer(Message message, Channel channel) {

        //1.打印消息
        StaticLog.info("DeadLetterConsumer receive message:[{}]", new String(message.getBody(), StandardCharsets.UTF_8));

        //2.手动确认消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
