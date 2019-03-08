/**
 * Created by Administrator on 2019/3/8.
 */
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("192.168.93.6:9876");
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("PushTopic", "push");

            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(
                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                                                 public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext Context) {
                                                     Message msg = list.get(0);
//                            System.out.println(msg.toString());

                                                     String topic = msg.getTopic();
                                                     System.out.println("topic = " + topic);
                                                     byte[] body = msg.getBody();
                                                     System.out.println("body:  " + new String(body));
                                                     String keys = msg.getKeys();
                                                     System.out.println("keys = " + keys);
                                                     String tags = msg.getTags();
                                                     System.out.println("tags = " + tags);
                                                     System.out.println("-----------------------------------------------");

                                                     return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                                 }
                                             }
            );
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}