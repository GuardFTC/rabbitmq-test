package com.ftc.ordermessage.service;

import cn.hutool.log.StaticLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-10-20 14:46:44
 * @describe: 消息处理业务服务类
 */
@Service
public class MessageService {

    /**
     * 控制台打印数字消息
     *
     * @param message 数字消息
     */
    @Async("rabbitListenerExecutor-1")
    public void consoleNumber(String message) {
        consoleMessage(message);
    }

    /**
     * 控制台打印字母消息
     *
     * @param message 字母消息
     */
    @Async("rabbitListenerExecutor-2")
    public void consoleLetter(String message) {
        consoleMessage(message);
    }

    /**
     * 控制台打印消息
     *
     * @param message 消息内容
     */
    private void consoleMessage(String message) {
        StaticLog.info(Thread.currentThread().getName() + " receive message:[{}]", message);
    }
}
