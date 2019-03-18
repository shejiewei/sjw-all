package com.sjw.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shejiewei on 2019/3/18.
 */
@Configuration
public class HiConfig {

    @Bean
    public Queue QueueA() {
      return new Queue("hi");
    }

    public Queue QueueB(){
        return new Queue("hi B");
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("ABExchange");
    }


    @Bean
    Binding bindingExchangeA(Queue QueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(QueueA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue QueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(QueueB).to(fanoutExchange);
    }



}
