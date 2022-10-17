package com.ftc.consumerack.config.callback;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-17 09:40:30
 * @describe: 发布消息到交换机回调确认
 */
@Component
@RequiredArgsConstructor
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 确认回调方法
     *
     * @param correlationData 回调消息的ID以及相关信息(收到消息为null)
     * @param ack             交换机是否收到消息(true表示收到)
     * @param cause           消息接收失败的原因(收到消息为null)
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            StaticLog.error("Message to exchange fail. Message is [{}],cause is [{}]", JSONUtil.toJsonStr(correlationData), cause);
        }
    }
}
