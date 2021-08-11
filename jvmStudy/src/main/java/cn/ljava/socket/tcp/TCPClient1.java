package cn.ljava.socket.tcp;

import java.io.*;
import java.net.Socket;

/***
 * TCP 客户端
 */
public class TCPClient1 {
    /***
     * 创建Socket连接服务端（需要指定ip，port）
     * 调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的IO流
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",6666);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        System.out.println(br.readLine());
        ps.println("要多读书");
        System.out.println(br.readLine());
        ps.println("你都多大了");
        socket.close();
    }
}
