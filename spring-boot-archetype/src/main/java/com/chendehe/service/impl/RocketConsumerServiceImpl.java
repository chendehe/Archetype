package com.chendehe.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.consumer.group}",
        topic = "${rocketmq.consumer.topic}",
        consumeMode = ConsumeMode.ORDERLY)
public class RocketConsumerServiceImpl implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("收到消息:" + msg);
    }
}
