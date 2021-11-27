package com.chendehe.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * nohup bin/mqnamesrv -n 10.211.55.5:9876 &
 * nohup sh bin/mqbroker -n 10.211.55.5:9876 -c conf/broker.conf autoCreateTopicEnable=true &
 * ./bin/mqadmin updateTopic -n localhost:9876  -b localhost:10911  -t topic-1
 */
@Slf4j
@Service
public class RocketServiceImpl {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String msg) {
        rocketMQTemplate.convertAndSend("topic-1", msg);
    }

    public void sendMessageAsync(String msg) {
        rocketMQTemplate.asyncSend("topic-1", msg, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("send success!{}", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("send error!", throwable);
            }
        });
    }
}
