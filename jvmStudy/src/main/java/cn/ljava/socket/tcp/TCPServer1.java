package cn.ljava.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer1 {
    public static void main(String[] args) throws IOException {
        final ServerSocket socket = new ServerSocket(6666);
        while (true){
            new Thread(()->{
                try{
                    //接受客户端的请求
                    Socket accept = socket.accept();
                    BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                    PrintStream ps = new PrintStream(accept.getOutputStream());
                    ps.println("好好学习吧");
                    System.out.println(br.readLine());
                    ps.println("一定得加油了");
                    System.out.println(br.readLine());
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
