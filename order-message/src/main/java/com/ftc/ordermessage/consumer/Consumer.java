package com.ftc.ordermessage.consumer;

import com.ftc.ordermessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-18 17:49:30
 * @describe: 消费者
 */
@Component
@RequiredArgsConstructor
public class Consumer {

    private final MessageService messageService;

    @RabbitListener(queues = {"order-queue"})
    public void consumer1(Message message) {

        //1.获取消息内容
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);

        //2.获取消息类型
        String type = message.getMessageProperties().getHeader("type");

        //3.取模法分发消息
        int index = Math.abs(type.hashCode() % 2);
        if (0 == index) {
            messageService.consoleLetter(messageBody);
        } else {
            messageService.consoleNumber(messageBody);
        }
    }
}
