package com.maizi.获取本地ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description:  直接使用getLocalHost()获取本机IP的错误示例
 * 运行程序，在不同的操作系统上值可能还不一样：
 *
 * 在windows上：172.18.0.162
 * 在windows上看似正常。但是但是但是，请你开一个vpn再运行试一下，定会让你大跌眼镜（比如我开启公司的vpn后，输出的值是2.0.0.137）
 * 可以看到当出现多个网卡接口工作时，windows可能就不好使了，而多个网卡同时工作的情况是很正常的（比较虚拟网卡经常很多）
 * 在Linux上：127.0.0.1
 * what？
 * 为何在Linux下请你一定不要使用它来获取本机IP，因为它就是简单的读取/etc/hosts的内容，所以它默认返回的是127.0.0.1非常的不靠谱，因此本方法十分不建议在生产上使用。
 *
 *  /etc/hosts的第一行一般均是：127.0.0.1 localhost，所以返回值是127.0.0.1（倘若你把第一行改为127.1.1.1 localhost，那么它的返回值就是127.1.1.1了）
 * @Author: Liuys
 * @CreateDate: 2020/6/9 13:29
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
    }
}
