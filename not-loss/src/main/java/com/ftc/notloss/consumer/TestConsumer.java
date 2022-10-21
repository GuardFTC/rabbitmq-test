package com.ftc.notloss.consumer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-20 16:32:53
 * @describe: 消费者
 */
@Component
public class TestConsumer {

    @SneakyThrows(IOException.class)
    @RabbitListener(queues = {"not-loss-queue"})
    public void consumer(Message message, Channel channel) {

        //1.获取消息内容
        String messageContent = new String(message.getBody(), StandardCharsets.UTF_8);

        //2.模拟业务流程出现异常
        if (StrUtil.isBlank(messageContent)) {

            //3.消费异常，Nack
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);

            //4.抛出异常
            throw new RuntimeException("Mock Exception");
        }

        //5.正常业务流程，控制台打印消息内容
        StaticLog.info("Consumer receive message:[{}]", messageContent);

        //6.手动消费确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
