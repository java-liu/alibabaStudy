package cn.ljava.socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test1_Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        //
        System.out.println("服务器启动");
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try{
                    //获取输入流
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    String line = br.readLine();
                    line = new StringBuilder(line).reverse().toString();
                    ps.println(line);
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
