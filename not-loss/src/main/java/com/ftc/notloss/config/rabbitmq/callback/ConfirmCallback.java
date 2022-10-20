package com.ftc.notloss.config.rabbitmq.callback;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import com.ftc.notloss.util.RabbitMqUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
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

        //1.获取消息ID
        String messageId = ObjectUtil.isNull(correlationData) ? "" : correlationData.getId();

        //2.发送成功,打印日志,返回
        if (ack) {
            StaticLog.info("Message to exchange success. Id is [{}]", messageId);
            return;
        }

        //3.发送失败打印日志
        StaticLog.error("Message to exchange fail. Id is [{}],cause is [{}]", messageId, cause);

        //4.获取ReturnMessage
        ReturnedMessage returned = correlationData.getReturned();

        //5.ReturnMessage为空,打印日志,返回
        if (ObjectUtil.isNull(returned)) {
            StaticLog.error("Message resend Fail. Id is [{}]", messageId);
            return;
        }

        //6.ReturnMessage，重新发送
        resend(returned);
    }

    /**
     * 重新发送消息
     *
     * @param returnedMessage 回调消息
     */
    private void resend(ReturnedMessage returnedMessage) {

        //1.获取消息属性
        String exchange = returnedMessage.getExchange();
        String routingKey = returnedMessage.getRoutingKey();
        Message message = returnedMessage.getMessage();

        //2.生成CorrelationData
        CorrelationData correlationData = RabbitMqUtil.getCorrelationData(exchange, routingKey, message);

        //3.发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }
}
