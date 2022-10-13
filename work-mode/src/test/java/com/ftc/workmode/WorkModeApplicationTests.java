package com.ftc.workmode;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class WorkModeApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @AfterEach
    @SneakyThrows(InterruptedException.class)
    void sleepSomeSeconds() {
        TimeUnit.MILLISECONDS.sleep(50);
    }

    @Test
    void SimpleModePublisher() {
        rabbitTemplate.convertAndSend("simple-mode-queue", "简单模式消息");
    }

    @Test
    void WorkModePublisher() {
        rabbitTemplate.convertAndSend("work-mode-queue", "工作模式消息第一条");
        rabbitTemplate.convertAndSend("work-mode-queue", "工作模式消息第二条");
    }

    @Test
    void PublishAndSubscribeModePublisher() {
        rabbitTemplate.convertAndSend("publish-subscribe-exchange", "", "发布订阅模式消息");
    }

    @Test
    void RouteModePublisher() {
        rabbitTemplate.convertAndSend("route-mode-exchange", "route-mode-queue1", "路由模式第一条消息");
        rabbitTemplate.convertAndSend("route-mode-exchange", "route-mode-queue2", "路由模式第二条消息");
        rabbitTemplate.convertAndSend("route-mode-exchange", "route-mode-queue3", "路由模式第三条消息");
    }

    @Test
    void TopicModePublisher1() {
        rabbitTemplate.convertAndSend("topic-mode-exchange", "topic1.one", "主题模式第一条消息");
        rabbitTemplate.convertAndSend("topic-mode-exchange", "topic1.two", "主题模式第二条消息");
        rabbitTemplate.convertAndSend("topic-mode-exchange", "topic2.two.one", "主题模式第三条消息");
        rabbitTemplate.convertAndSend("topic-mode-exchange", "topic2.two.two", "主题模式第四条消息");
        rabbitTemplate.convertAndSend("topic-mode-exchange", "topic2.two.three", "主题模式第五条消息");
    }
}
