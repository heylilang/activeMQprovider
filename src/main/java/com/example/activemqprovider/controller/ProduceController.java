package com.example.activemqprovider.controller;


import com.example.activemqprovider.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceController {
    @Autowired
    private ProduceService produceService ;

    @RequestMapping("/queue")
    @ResponseBody
    public String sendQueue(){
        String msgStr = System.currentTimeMillis() + ".MQ.Queue" ;
        produceService.sendMsg(msgStr);
        System.out.println("点对点通信， message：" + msgStr);
        return msgStr ;
    }

    @RequestMapping("/topic")
    @ResponseBody
    public String sendTopic(){
        String msgStr = System.currentTimeMillis() + ".MQ.Topic" ;
        produceService.publish(msgStr);
        System.out.println("发布订阅模式通信， message：" + msgStr);
        return msgStr ;
    }
}
