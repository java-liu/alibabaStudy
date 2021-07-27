package cn.ljava.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/***
 * Mq 连接工厂创建信道的工具类
 */
public class RabbitMqUtils {
    public static Channel getChannel() throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("182.92.76.182");
        factory.setUsername("admin1");
        factory.setPort(5672);
        factory.setPassword("java_2021");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
