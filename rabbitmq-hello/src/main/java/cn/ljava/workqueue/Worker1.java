package cn.ljava.workqueue;

import cn.ljava.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 * 消费者
 */
public class Worker1 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag, message) ->{
            System.out.println("接收到的消息："+ new String(message.getBody()));
        };

        //消息接收被取消时 执行下面的内容
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };
        /***
         * 消费者消费消息
         * 1、消费哪个队列
         * 2、消费成功之后 是否要自动应答 true 代表自动应答 实际中少用
         * 3、消费者未成功消费的回调
         * 4、消息接收被取消时，执行的内容
         */
        System.out.println("C2等待接收消息......");
        channel.basicConsume(QUEUE_NAME, true,deliverCallback, cancelCallback);
    }

}
