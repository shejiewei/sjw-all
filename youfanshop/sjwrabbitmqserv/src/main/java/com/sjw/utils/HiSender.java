package com.sjw.utils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shejiewei on 2019/3/19.
 */
@Service
public class HiSender implements RabbitTemplate.ReturnCallback {

     @Autowired
     private  RabbitTemplate rabbitTemplate;

     public  void send(){
         String context="hi"+new Date();
         System.out.println("发送的内容是:"+context);

         this.rabbitTemplate.setReturnCallback(this);
         this.rabbitTemplate.setConfirmCallback(((correlationData, ack, casuse) -> {
             if(!ack){
                 System.out.println("发送消息失败"+casuse);

             }
             else
             {
                 System.out.println("发送消息成功");
             }
         }));

         this.rabbitTemplate.convertAndSend("hi",context);
     }



    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

        System.out.println("发送返回的消息"+message.toString());
    }
}
