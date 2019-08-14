package com.maizi.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * FTPS上传工具类
 *
 * @author J.query
 * @date 2018/11/8
 * @email j-query@foxmail.com
 */

public class FTPSUploadUtils {
    /**
     * @param host
     * @param port
     * @param userName       用户名
     * @param password       密码
     * @param basePath       服务器文件根目录
     * @param localFileName  文件本地地址
     * @param remoteFileName 服务器实际目录
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static boolean upLoadFile(String host, int port, String userName, String password, String basePath, String localFileName,
                                     String remoteFileName) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        boolean result = false;

        final int dataTimeout = 10000;
        final int timeout = 100000;
        X509TrustManager x509m = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        };
        SSLContext sslcontext = SSLContext.getInstance("SSL");
        sslcontext.init(null, new TrustManager[]{x509m},
                new java.security.SecureRandom());
        FTPSClient ftpClient = new FTPSClient(false, sslcontext);

        try {
            ftpClient.setConnectTimeout(dataTimeout);
            ftpClient.connect(host, port);
            ftpClient.setSoTimeout(timeout);
            // Set protection buffer size
            int reply = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                // Login
                if (ftpClient.login(userName, password)) {
                    //此设置关键之关键，耗费了1天时间，大坑
                    ftpClient.execPBSZ(0);
                    ftpClient.execPROT("P");
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    // make directory
                    if (basePath != null && !"".equals(basePath.trim())) {
                        String[] pathes = basePath.split("/");
                        for (String onepath : pathes) {
                            if (onepath == null || "".equals(onepath.trim())) {
                                continue;
                            }
                            onepath = new String(onepath.getBytes("GBK"), "iso-8859-1");
                            if (!ftpClient.changeWorkingDirectory(onepath)) {
                                ftpClient.makeDirectory(onepath);
                                ftpClient.changeWorkingDirectory(onepath);
                            }
                        }
                    }
                    File file = new File(localFileName);
                    if (file.exists()) {
                        InputStream is = new FileInputStream(localFileName);
                        result = ftpClient.storeFile(remoteFileName, is);
                        is.close();
                        ftpClient.logout();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ftpClient.logout();
        }
        return result;
    }
}
