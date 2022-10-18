package com.ftc.queue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LazyQueueTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testLazyMessage() {

        //1.定义消息内容
        String message = "我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春我是大傻春";

        //2.发送1000条消息
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("lazy-exchange", "lazy-queue", message);
        }
    }
}
