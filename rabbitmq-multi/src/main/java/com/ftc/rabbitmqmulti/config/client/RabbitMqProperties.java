package com.ftc.rabbitmqmulti.config.client;

import lombok.Data;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-12 15:00:10
 * @describe: RabbitMq配置类
 */
@Data
public class RabbitMqProperties {

    /**
     * 服务器地址，多个用逗号分割
     */
    private String host;

    /**
     * 端口号
     */
    private int port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 虚拟机
     */
    private String virtualHost;

    /**
     * 消费确认模式 NONE/AUTO/MANUAL
     */
    private String acknowledgeMode;
}
