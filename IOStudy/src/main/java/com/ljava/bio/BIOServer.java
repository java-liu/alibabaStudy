package com.ljava.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws Exception {
        //线程池机制
        //1.创建一个线程池
        //2. 如果有客户端连接,就创建一个线程,与之通讯
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动了");
        while(true){
            System.out.println("thread info id= " + Thread.currentThread().getId() + ",name = " + Thread.currentThread().getName());
            //监听,等待客户端连接
            System.out.println("等待连接....");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //就创建一个线程,与之通讯(单独写一个方法)
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //可以和客户端通讯
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private static void handler(Socket socket) throws IOException {
        try {
            System.out.println("thread info id= " + Thread.currentThread().getId() + "name = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while(true){
                System.out.println("thread info id= " + Thread.currentThread().getId() + "name = " + Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("close client 连接");
            try{
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
