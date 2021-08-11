package cn.ljava.socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Test1_Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1",6666);
        //获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        PrintStream ps = new PrintStream(socket.getOutputStream());
        //将字符串写到服务器
        ps.println(sc.nextLine());
        //读取服务端发送来的数据
        System.out.println(br.readLine());
    }
}
