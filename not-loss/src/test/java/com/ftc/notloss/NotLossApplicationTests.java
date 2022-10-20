package com.ftc.notloss;

import com.ftc.notloss.util.RabbitMqUtil;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class NotLossApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testPublisherToExchangeLoss() {

        //1.定义基本信息
        String errorExchange = "undefined";
        String exchange = "not-loss-exchange";
        String routingKey = "not-loss-queue";
        String messageContent = "Publisher=>Exchange丢失消息";

        //2.获取MessageCorrelationData、correlationData
        Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8));
        CorrelationData correlationData = RabbitMqUtil.getCorrelationData(exchange, routingKey, message);

        //3.发送消息
        rabbitTemplate.convertAndSend(errorExchange, routingKey, message, correlationData);
    }

    @Test
    void testExchangeToQueueLoss() {

        //1.定义基本信息
        String exchange = "not-loss-exchange";
        String errorRoutingKey = "undefined";
        String routingKey = "not-loss-queue";
        String messageContent = "Exchange=>Queue丢失消息";

        //2.获取MessageCorrelationData、correlationData
        Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8));
        CorrelationData correlationData = RabbitMqUtil.getCorrelationData(exchange, routingKey, message);
        message = RabbitMqUtil.setMessageIdToHeader(message, correlationData.getId());

        //3.发送消息
        rabbitTemplate.convertAndSend(exchange, errorRoutingKey, message, correlationData);
    }

    @Test
    void testBrokerLoss() {

        //1.创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        Message message = new Message("Broker丢失消息".getBytes(StandardCharsets.UTF_8), messageProperties);

        //2.发送消息
        rabbitTemplate.convertAndSend("not-loss-exchange", "not-loss-queue", message);
    }

    @Test
    void testConsumerLoss() {
        rabbitTemplate.convertAndSend("not-loss-exchange", "not-loss-queue", "Consumer丢失消息");
    }
}
