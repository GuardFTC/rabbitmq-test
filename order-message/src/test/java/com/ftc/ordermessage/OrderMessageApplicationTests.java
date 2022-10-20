package com.ftc.ordermessage;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class OrderMessageApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    @SneakyThrows
    void testOrder() {

        //1.发送数字类型的顺序消息
        for (int i = 1; i <= 10; i++) {

            //2.生成消息
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("type", "number");
            Message message = new Message((i + "").getBytes(StandardCharsets.UTF_8), messageProperties);

            //3.发送
            rabbitTemplate.send("order-exchange", "order-queue", message);

            //4.睡一下
            TimeUnit.MILLISECONDS.sleep(5);
        }

        //5.发送字母类型的顺序消息
        for (int i = 0; i < 26; i++) {

            //6.生成消息
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("type", "letter");
            Message message = new Message(((char) (97 + i) + "").getBytes(StandardCharsets.UTF_8), messageProperties);

            //7.发送
            rabbitTemplate.send("order-exchange", "order-queue", message);

            //8.睡一下
            TimeUnit.MILLISECONDS.sleep(5);
        }

        //9.睡会，不然线程结束了没法消费消息
        TimeUnit.SECONDS.sleep(5);
    }
}
