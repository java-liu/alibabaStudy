package cn.ljava.three;

import cn.ljava.utils.RabbitMqUtils;
import cn.ljava.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work04 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("C2等待接收消息处理时间长");

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag, message) ->{
            //沉睡1s
            SleepUtils.sleep(10);
            System.out.println("接收到的消息："+ new String(message.getBody(),"UTF-8"));
            //手动应答
            /***
             * 1、消息的标记，tag
             * 2、是否批量应答 false：不批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        //消息接收被取消时 执行下面的内容
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };
        //采用手动应答
        boolean autoAck = false;
        //设置成不公平分发，也可以是预取值
        int prefetchCount = 5;
        channel.basicQos(prefetchCount);
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback,(consumerTag -> {
            System.out.println(consumerTag + "消费者取消了接口回调逻辑");
        }));
    }
}
