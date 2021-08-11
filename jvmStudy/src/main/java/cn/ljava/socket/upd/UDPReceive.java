package cn.ljava.socket.upd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/***
 * UDP 接收端
 */
public class UDPReceive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);
        while (true){
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            //获取数据
            byte[] arr = packet.getData();
            //获取有效的字节数
            int len = packet.getLength();
            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();
            System.out.println(ip+ ":"+ port + new String(arr,0 ,len));

        }

        //socket.close();
    }
}
