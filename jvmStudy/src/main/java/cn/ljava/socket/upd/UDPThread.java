package cn.ljava.socket.upd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/***
 * UDP用多线程实现发送和接收
 */
public class UDPThread {
    public static void main(String[] args) {
        new Thread(new Receive()).start();
        new Thread(new Send()).start();
    }
}

class Receive implements Runnable{
    @Override
    public void run() {
        try{
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
                System.out.println(ip+ ":"+ port +":" + new String(arr,0 ,len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
class Send implements Runnable{

    @Override
    public void run() {
        try{
            Scanner sc = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            while (true){
                String line = sc.nextLine();
                if("quit".equals(line)){
                    break;
                }
                DatagramPacket packet = new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName("127.0.0.1"),6666);
                socket.send(packet);
            }
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
