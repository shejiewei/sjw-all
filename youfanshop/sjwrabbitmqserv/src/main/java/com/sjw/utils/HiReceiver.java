package com.sjw.utils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by shejiewei on 2019/3/19.
 */


@Component
@RabbitListener(queues ="hi")
public class HiReceiver {
    @RabbitHandler
    public  void process(String hello,Channel channel,Message message) throws IOException {


        try { System.out.println("收到"+hello+new Date());

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

            System.out.println("接受成功");

        } catch (IOException e) {



            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
             System.out.println("接受失败");

        }


    }

}
