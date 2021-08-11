package cn.ljava.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(6666);
        //接受客户端的请求
        Socket accept = socket.accept();
        InputStream is = accept.getInputStream();
        OutputStream os = accept.getOutputStream();

        os.write("百度一下".getBytes());

        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0 ,len));
        socket.close();
    }
}
