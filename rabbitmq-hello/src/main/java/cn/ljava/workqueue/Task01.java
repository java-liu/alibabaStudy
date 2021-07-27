package cn.ljava.workqueue;

import cn.ljava.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task01 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();

            /***
             * 发送一个消息
             * 1、发送到哪个交换机，此处没有
             * 2、路由的key值是哪个  本次是队列的名称
             * 3、其他参数信息
             * 4、发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME,null ,message.getBytes());
            System.out.println("发送消息完成"+ message);
        }
    }
}
