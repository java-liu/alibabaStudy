package com.maizi.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore;
/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.ftp
 * @date 2019/8/2 17:34
 */
public class ConnectFTPS {
    private static FTPSClient ftpsClient;
    private static final String trust_path = "D:/zhxd.keystore";
    private static final String trust_pw = "123456";
    private static final String key_path = "D:/zhxd.keystore";
    private static final String key_pw = "123456";
    private static final String serverIP = "127.0.0.1";
    private static final int serverPort = 2121;
    private static final int defaultTimeout = 10000;
    private static final int soTimeout = 900000;
    private static final int dataTimeout = 5000;
    /**
     * 测试连接FTP With SSL，以Apache FTPServer为例
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        if (!connect("active"))
        {
            connect("passive");
        }
        FileInputStream fs = new FileInputStream(trust_path);
        System.out.println("storeFile: " + ftpsClient.storeFile("test_file", fs));
        fs.close();
        ftpsClient.disconnect();
    }
    /**
     * 登陆FTP
     * @param active
     * @return
     * @throws Exception
     */
    private static boolean connect(String active) throws Exception
    {
        ftpsClient = new FTPSClient( "TLS",true);
        ftpsClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpsClient.setKeyManager(getKeyManager());
        //ftpsClient.setTrustManager(getTrustManager());
        ftpsClient.setDefaultTimeout(defaultTimeout);
        ftpsClient.connect(serverIP, serverPort);
        System.out.println("已经连接FTP");
        ftpsClient.setSoTimeout(soTimeout);
        ftpsClient.getReplyCode();
        ftpsClient.execPBSZ(0);
        ftpsClient.execPROT("P");
        ftpsClient.login("liuys", "123456");
        ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpsClient.changeWorkingDirectory("/");
        ftpsClient.setDataTimeout(dataTimeout);
        if (active.equalsIgnoreCase("active"))
        {
            ftpsClient.enterLocalActiveMode();
        } else
        {
            ftpsClient.enterLocalPassiveMode();
        }

        System.out.println("已经登陆FTP");
        return testLink();
    }
    /**
     * 遍历FTP文件
     * @return
     */
    private static boolean testLink()
    {
        long t1 = System.currentTimeMillis();
        try
        {
            System.out.println("List file length:" + ftpsClient.listFiles().length);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            long t2 = System.currentTimeMillis();
            long t = (t2 - t1) / 1000;
            System.out.println("t: " + t);
            try
            {
                ftpsClient.disconnect();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }
    private static KeyManager getKeyManager() throws Exception
    {
        KeyStore key_ks = KeyStore.getInstance("JKS");
        key_ks.load(new FileInputStream(key_path), key_pw.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(key_ks, key_pw.toCharArray());
        KeyManager[] km = kmf.getKeyManagers();
        System.out.println("km len: " + km.length);
        return km[0];
    }
    private static TrustManager getTrustManager() throws Exception
    {
        KeyStore trust_ks = KeyStore.getInstance("JKS");
        trust_ks.load(new FileInputStream(trust_path), trust_pw.toCharArray());
        TrustManagerFactory tf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tf.init(trust_ks);
        TrustManager[] tm = tf.getTrustManagers();
        System.out.println("tm len: " + tm.length);
        return tm[0];
    }

}
