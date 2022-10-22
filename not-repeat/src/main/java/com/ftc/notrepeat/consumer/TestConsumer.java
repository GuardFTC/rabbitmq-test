package com.ftc.notrepeat.consumer;

import cn.hutool.log.StaticLog;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-20 16:32:53
 * @describe: 消费者
 */
@Component
public class TestConsumer {

    /**
     * 接收消息次数
     */
    private static Integer RECEIVE_TIME = 0;

    @RabbitListener(queues = {"not-repeat-queue"})
    public void consumer(String message) {

        //1.接收次数++
        RECEIVE_TIME++;

        //1.打印消息,通过RECEIVE_TIME模拟锁机制
        if (RECEIVE_TIME == 1) {
            StaticLog.info("consumer receive message:[{}]", message);
        }

        //2.出现异常,模拟通过重试异常被解决
        if (RECEIVE_TIME != 3) {
            int error = 1 / 0;
        }

        //3.打印消费完成字样，模拟消息彻底消费完成
        StaticLog.info("消息消费完成");
    }
}
