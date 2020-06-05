package com.czkj.rocket.producer;

import com.alibaba.fastjson.JSON;
import com.czkj.rocket.vo.UserContent;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author steven.sheng
 * @Date 2019/6/12/01211:24
 */
@Component
public class Producer1 {

    /**
     * 生产者的组名
     */
    @Value("${rocketmq.config.producerGroup}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${rocketmq.config.namesrvAddr}")
    private String namesrvAddr;

    @PostConstruct
    public void produder() {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
            int size = 100;
            for (int i = 0; i < size; i++) {
                UserContent userContent = new UserContent();
                userContent.setName("123123");
                userContent.setAddr("123123");
                String jsonstr = JSON.toJSONString(userContent);
                System.out.println("发送消息:" + jsonstr);
                Message message = new Message("user-topic", "user-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = producer.send(message);
                System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
