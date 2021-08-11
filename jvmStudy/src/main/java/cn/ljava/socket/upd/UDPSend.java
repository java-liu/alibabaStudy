package cn.ljava.socket.upd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/***
 * UDP发送端
 */
public class UDPSend {
    public static void main(String[] args) throws Exception {
        //String str = "what are you 弄啥？";
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
    }
}
