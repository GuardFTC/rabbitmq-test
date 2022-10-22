package com.ftc.notrepeat.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.nio.charset.StandardCharsets;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-20 18:38:09
 * @describe: RabbitMq相关工具类
 */
public class RabbitMqUtil {

    /**
     * 默认消息回调响应码
     */
    public static final int REPLY_CODE = 0;

    /**
     * 默认消息回调文本
     */
    public static final String REPLY_TEXT = "";

    /**
     * 消息配置-消息头-消息IDKey
     */
    public static final String MESSAGE_ID_HEADER = "MESSAGE_ID_HEADER";

    /**
     * 生成CorrelationData
     *
     * @param exchange   交换机
     * @param routingKey 消息路由Key
     * @param message    消息
     * @return CorrelationData
     */
    public static CorrelationData getCorrelationData(String exchange, String routingKey, Message message) {

        //1.生成消息ID
        String messageId = IdUtil.nanoId();
        StaticLog.info(new String(message.getBody(), StandardCharsets.UTF_8) + " id is:[{}]", messageId);

        //2.创建ReturnMessage
        ReturnedMessage returnedMessage = new ReturnedMessage(message, REPLY_CODE, REPLY_TEXT, exchange, routingKey);

        //3.生成CorrelationData
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(messageId);
        correlationData.setReturned(returnedMessage);

        //4.返回
        return correlationData;
    }

    /**
     * 在消息头中设置消息ID
     *
     * @param message   消息
     * @param messageId 消息ID
     * @return 设置消息头后的消息
     */
    public static Message setMessageIdToHeader(Message message, String messageId) {

        //1.获取消息头
        MessageProperties messageProperties = message.getMessageProperties();
        if (ObjectUtil.isNull(messageProperties)) {
            messageProperties = new MessageProperties();
        }

        //2.设置消息ID到消息头
        messageProperties.setHeader(MESSAGE_ID_HEADER, messageId);
        message = new Message(message.getBody(), messageProperties);

        //3.返回
        return message;
    }
}
