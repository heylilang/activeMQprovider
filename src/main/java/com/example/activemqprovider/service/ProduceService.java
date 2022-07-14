package com.example.activemqprovider.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
public class ProduceService {

    @Autowired
    private Queue queue ;

    @Resource
    private JmsMessagingTemplate  jmsTemplate ; //用来发送消息到broker的对象

    /**
     * 发送消息
     * @param destinationName 是发送到的队列名
     * @param message 是待发送的消息
     */
    public void sendMsg(String destinationName, String message){
        System.out.println("发送消息=====>>>>>:" + message);

        Destination destination = new ActiveMQQueue(destinationName) ;
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * message是待发送的消息
     * @param message  是待发送的消息
     */
    public void sendMsg(final String message){
        System.out.println("发送消息=====>>>>>:" + message);

        jmsTemplate.convertAndSend(this.queue, message);
    }

    //=======发布订阅相关代码=========
    @Autowired
    private Topic topic;

    public void publish(String msg) {
        this.jmsTemplate.convertAndSend(this.topic, msg);
    }

}