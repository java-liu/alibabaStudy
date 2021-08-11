package cn.ljava.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/***
 * TCP 客户端
 */
public class TCPClient {
    /***
     * 创建Socket连接服务端（需要指定ip，port）
     * 调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的IO流
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",6666);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();


        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0 ,len));
        os.write("学习哪家强".getBytes());
        socket.close();
    }
}
