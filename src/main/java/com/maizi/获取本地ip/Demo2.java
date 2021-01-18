package com.maizi.获取本地ip;

import com.sun.jmx.snmp.InetAddressAcl;
import sun.nio.ch.Net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @Description: 获取本机IP地址的正确姿势
 * 简单情况（废弃）
 * 简单情况下，就可以通过InetAddress.getLocalHost()来获取到本机ip地址。注意这里的关键词：简单。因此它对环境是有要求的：
 *
 * windows环境
 * 非多网卡协同工作环境（比如不能开启vpn）
 * 很明显，这种“简单”情况在实际生产中并不存在，因此仅限yy，不可使用。
 *
 * 复杂情况（通用，推荐的方案）
 * @Author: Liuys
 * @CreateDate: 2020/6/9 15:40
 * @Version: 1.0
 */
public class Demo2 {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());


        System.out.println("----------------下面才是正确的获取方式----------------");
        localHost = getLocalHostExactAddress();
        System.out.println(localHost.getHostName());
    }

    public static InetAddress getLocalHostExactAddress(){
        try{
            InetAddress candidateAddress = null;

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()){
                NetworkInterface iface = networkInterfaces.nextElement();
                //该网卡接口下的ip会有多个，也需要一个个的遍历，找到自己需要的。
                for(Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();){
                    InetAddress inetAddr = inetAddrs.nextElement();
                    //排除loopback回环类型地址（不管是IPv4还是Ipv6 只要是回环地址都会返回true）
                    if(!inetAddr.isLoopbackAddress()){
                        if(inetAddr.isSiteLocalAddress()){
                            // 如果是site-local地址，就是它了，就是我们想要的
                            // ~~~~~~~~~~~~~~~~~~~绝大部分情况下都会在此返回ip地址值~~~~~~~~~~~~~~~~
                            return inetAddr;
                        }
                        //若不是site-local地址，那就记录下该地址当作候选
                        if(candidateAddress == null){
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            //如果除去loopback回环之外无其他地址了，那就回退到原始方案
            return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
