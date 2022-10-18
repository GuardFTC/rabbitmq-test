package com.ftc.ordermessage.config.callback;

import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-17 09:40:30
 * @describe: 交换机发送消息到队列回调确认
 */
@Component
@RequiredArgsConstructor
public class ReturnsCallback implements RabbitTemplate.ReturnsCallback {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 返回异常回调方法
     *
     * @param returnedMessage 返回消息及其元数据
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

        //1.获取异常数据
        Message message = returnedMessage.getMessage();
        int replyCode = returnedMessage.getReplyCode();
        String replyText = returnedMessage.getReplyText();
        String exchange = returnedMessage.getExchange();
        String routingKey = returnedMessage.getRoutingKey();

        //2.日志打印
        StaticLog.error("Message to queue fail. MessageId is [{}],replyCode is [{}],replyText is [{}]", message.getMessageProperties().getMessageId(), replyCode, replyText);
    }
}
