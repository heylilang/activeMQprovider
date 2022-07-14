package com.example.activemqprovider.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class JmsConfig {

    @Value("${mq.queue}")
    private String mqQueue;

    @Value("${mq.topic}")
    private String mqTopic;

    //交给spring管理，方便后续注入
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(mqQueue);
    }

    //将主题对象交给spring管理
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(mqTopic);
    }

}
