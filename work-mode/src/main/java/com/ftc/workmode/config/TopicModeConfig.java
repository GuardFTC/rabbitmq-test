//package com.ftc.workmode.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author: 冯铁城 [17615007230@163.com]
// * @date: 2022-10-12 10:47:47
// * @describe: 简单模式组件配置
// */
//@Configuration
//public class TopicModeConfig {
//
//    /**
//     * 默认交换机
//     */
//    private static final String DEFAULT = "AMQP default";
//
//    /**
//     * 直连交换机
//     */
//    private static final String DIRECT = "amq.direct";
//
//    /**
//     * 扇形交换机
//     */
//    private static final String FANOUT = "amq.fanout";
//
//    /**
//     * 主题交换机
//     */
//    private static final String TOPIC = "amq.topic";
//
//    @Bean
//    public Queue simpleModeQueue() {
//        return new Queue("simple-mode-queue", false);
//    }
//}
