package com.ftc.notloss.consumer;

import cn.hutool.core.util.StrUtil;
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

    @RabbitListener(queues = {"not-loss-queue"})
    public void consumer(String message) {

//        //1.模拟业务流程出现异常
//        if (StrUtil.isNotBlank(message)) {
//            throw new RuntimeException("Mock Exception");
//        }

        //2.正常业务流程，控制台打印消息内容
        StaticLog.info("Consumer receive message:[{}]", message);
    }
}
