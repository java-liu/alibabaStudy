package com.maizi.ftp;


import com.maizi.utils.StringExtend;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName FtpsClient
 * @Description
 * @Author yongshan.liu
 * @Date 2019/4/2 10:38
 * DGG-XAM03 2019/5/24 修改，不能使用static的FTPSClient，多线程处理会引起异常
 * @Verson 1.0
 **/
public class FtpsClient {
    private static Logger logger = LoggerFactory.getLogger(FtpsClient.class);
    private static final String key_path = "D:\\liuys\\apache-ftpserver-1.1.1\\res\\ftp.jks";
    private static final String key_pw = "123456";

    /**
     * 上传文件到目标服务器方法2 ，优化了传输速度
     *
     * @param url      Ftp服务器端口
     * @param port     ftp服务器端口
     * @param username ftp登录账号
     * @param password ftp登录密码
     * @param path     ftp服务器保存目录
     * @param fileName 上传到ftp服务器上的文件名
     * @param filepath 本地文件路径
     * @return boolean
     * @throws
     * @author yongshan.liu
     * @date 2019/4/16 9:24
     */
    public static boolean uploadToFtpServer(String url, int port, String username, String password, String path, String fileName, String filepath) {
        try {
            FTPSClient ftp = getFtpsClient(url, port, username, password);
            //设置文件名字的编码格式为iso-8859-1，因为FTP上传的时候默认编码为iso-8859-1，解决文件名字乱码的问题,可有可能含中文文件名传不上去
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            FileInputStream input = new FileInputStream(new File(filepath));
            return writeFile2Ftp(ftp, input, path, fileName);
        } catch (Exception ex) {
            logger.error("ftp上传异常", ex);
        }
        return false;
    }


    /**
     * @param url
     * @param port
     * @param username
     * @param password
     * @param path
     * @param file
     * @param fileName
     * @return boolean
     * @Author DGG-XAM03
     * @Description MultipartFile文件上传到ftp
     * @Date 2019/5/24
     **/
    public static boolean multipartFileToFtpServer(String url, int port, String username, String password, String path, MultipartFile file, String fileName) {
        try {
            FTPSClient ftp = getFtpsClient(url, port, username, password);
            return writeFile2Ftp(ftp, file.getInputStream(), path, fileName);
        } catch (Exception ex) {
            logger.error("ftp上传异常", ex);
        }
        return false;
    }

    /**
     * @param url
     * @param port
     * @param username
     * @param password
     * @return org.apache.commons.net.ftp.FTPSClient
     * @Author DGG-XAM03
     * @Description 获取ftp链接对象
     * @Date 2019/5/24
     **/
    private static FTPSClient getFtpsClient(String url, int port, String username, String password) throws Exception {
        //FTPSClient ftp = new FTPSClient();
        //连接到FTP服务器
        final int dataTimeout = 10000;
        final int timeout = 100000;
        X509TrustManager x509m = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
                //return new X509Certificate[0];
            }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        //sslContext.init(null,new TrustManager[]{x509m},new java.security.SecureRandom());
        //sslContext.init(null,getTrustManagers(),new java.security.SecureRandom());
        //FTPSClient ftpsClient = new FTPSClient(false,sslContext);
        FTPSClient ftpsClient = new FTPSClient(true);
        ftpsClient.setConnectTimeout(dataTimeout);
        try {
            //ftpsClient.setKeyManager(getKeyManager());
            ftpsClient.setTrustManager(getTrustManagers()[0]);
            ftpsClient.connect(url, port);
            boolean flag = ftpsClient.login(username, password);
            ftpsClient.setSoTimeout(timeout);
            //ftp返回码
            int reply = ftpsClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                //没有权限
                ftpsClient.disconnect();
                throw new Exception("没有ftp权限");
            }else{
                ftpsClient.execPBSZ(0);
                ftpsClient.execPROT("P");
                ftpsClient.enterLocalPassiveMode();
                ftpsClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpsClient.enterLocalPassiveMode();
            }
            return ftpsClient;
        } catch (Exception ex) {
            logger.error("ftp链接异常", ex);
            if (ftpsClient.isConnected()) {
                //关闭ftp
                ftpsClient.disconnect();
            }
            throw new Exception("ftp链接已满");
        }
    }

    /**
     * @param ftpsClient
     * @param input
     * @param path
     * @param fileName
     * @return void
     * @Author DGG-XAM03
     * @Description FileInputStream 上传到ftp服务器
     * @Date 2019/5/24
     **/
    private static boolean writeFile2Ftp(FTPSClient ftpsClient, InputStream input, String path, String fileName) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        //boolean result = true;
        try {
            //如果上传时自定义了文件路径，需要先创建文件夹
            createDir(ftpsClient, path);
            //改变工作空间到新创建的文件夹下
//            ftp.changeWorkingDirectory(path);
            //设置文件以二进制形式上传，防止文件内容出现乱码
            ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpsClient.enterLocalPassiveMode();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            OutputStream os = ftpsClient.storeFileStream(path + "/" + fileName);
            //OutputStream oss = ftpsClient.storeFile();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = input.read(b)) != -1) {
                os.write(b, 0, len);
            }
            input.close();
            os.close();
            ftpsClient.logout();
            ftpsClient.disconnect();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ftpsClient.isConnected()) {
                try {
                    //关闭ftp
                    ftpsClient.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 根据dirname在目标服务器上创建文件夹
     *
     * @param dirname 文件夹名称
     * @return void
     * @throws
     * @author yongshan.liu
     * @date 2019/4/16 14:44
     */
    public static boolean createDir(FTPSClient ftp, String dirname) {
        if (StringUtils.isBlank(dirname))
            return true;
        String d;
        try {
            d = new String(dirname.toString().getBytes("GBK"), "iso-8859-1");
            if (ftp.changeWorkingDirectory(d)) {
                return true;
            }
            dirname = StringExtend.trimStart(dirname, "/");
            dirname = StringExtend.trimEnd(dirname, "/");
            String[] arr = dirname.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录
            for (String s : arr) {
                sbfDir.append("/");
                sbfDir.append(s);
                d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                if (ftp.changeWorkingDirectory(d)) {
                    continue;
                }
                if (!ftp.makeDirectory(d)) {
                    logger.info("[失败]ftp创建目录：" + sbfDir.toString());
                    return false;
                }
                logger.info("[成功]ftp创建目录：" + sbfDir.toString());
            }
            //logger.info("在目标服务器上成功建立了文件夹：" + dirname);
            return ftp.changeWorkingDirectory(d);
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    private static TrustManager[] getTrustManagers() throws Exception{
        //String key = "D:/mykey.keystore";
        String key = "D:\\liuys\\apache-ftpserver-1.1.1\\res\\ftptrustore.jks";
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(key),"123456".toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(keyStore);
        return tmf.getTrustManagers();
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

    /**
     * 关闭ftp连接
     *
     * @param
     * @return
     * @throws
     * @author yongshan.liu
     * @date 2019/4/18 16:11
     */
    public static void main(String[] args) throws Exception {
        try {
            FileInputStream in = new FileInputStream(new File("C:\\Users\\14258\\Desktop\\tomcat.txt"));
            String filePath = "C:\\Users\\14258\\Desktop\\tomcat.txt";

            boolean flag = uploadToFtpServer("127.0.0.1", 2121, "liuys", "123456", "/test/image", "tomcat.txt", filePath);
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
