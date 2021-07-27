package cn.ljava.four;


import cn.hutool.core.lang.UUID;
import cn.ljava.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/***
 * 验证发布确认模式
 * 发布确认模式：
 * 1、单个确认
 * 2、批量确认
 * 3、异步批量确认
 */
public class ConfirmMessage {
    public static final int  MESSAGE_COUNT = 1000;
    public static final String queue_name = "";

    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
        //ConfirmMessage.publishMessageIndividually();
        //ConfirmMessage.publishMessageBatch();
        ConfirmMessage.publicMessageAsync();
    }

    //单个确认
    public static void publishMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();

        //队列的声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        channel.confirmSelect();
        long begin = System.currentTimeMillis();

        for(int i = 0; i < MESSAGE_COUNT; i++){
            String message = i + "";
            channel.basicPublish("", queueName,null, message.getBytes());
            boolean flag = channel.waitForConfirms();
            if(flag){
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT +"个单独确认消息，总共用时 = " + (end - begin));
    }
    //批量确认
    public static void publishMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();

        //队列的声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize = 100;
        for(int i = 0; i < MESSAGE_COUNT; i++){
            String message = i + "";
            channel.basicPublish("", queueName,null, message.getBytes());
            //判断达到100条消息的时候，批量确认一次
            if(i%batchSize == 0){
                channel.waitForConfirms();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT +"个批量确认消息，总共用时 = " + (end - begin));
    }
    //异步发布确认
    public static void publicMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();

        //队列的声明
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        /***
         * 线程安全有序的一个hash表，适用于高并发情况下
         * 1、轻松的将序号与消息进行关联
         * 2、轻松批量删除条目，只要给到序号
         * 3、支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
        //消息确认成功，回调函数
        ConfirmCallback ackCallback = (deliveryTag,  multiple) ->{
            //删除已经确认的消息
            if(multiple){
                //批量确认
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息：" + deliveryTag);
        };
        //消息确认失败，回调函数
        ConfirmCallback nackCallback = (deliveryTag,  multiple) ->{
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息是：" + message + "====未确认的消息tag：" + deliveryTag);
        };
        //准备消息的监听器，监听哪些消息成功了，哪些消息失败了
        //ConfirmCallback ackCallback 成功的, ConfirmCallback nackCallback失败的
        /***
         * 1、监听哪些消息成功了
         * 2、监听哪些消息失败了
         */
        channel.addConfirmListener(ackCallback, nackCallback);
        //批量发送消息
        for(int i = 0; i < MESSAGE_COUNT; i++){
            String message = i + "";
            channel.basicPublish("", queueName,null, message.getBytes());
            //此处记录下所有要发送的消息，消息的总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }

        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT +"个批量确认消息，总共用时 = " + (end - begin));
    }
}
