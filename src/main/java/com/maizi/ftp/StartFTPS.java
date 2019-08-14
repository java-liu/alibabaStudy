package com.maizi.ftp;

import java.io.File;
import java.io.File;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.ftp
 * @date 2019/8/2 17:27
 */
public class StartFTPS {
    /**
     * 通过程序启动FTP with SSL认证，以Apache FTPServer为例
     * @param args
     * @throws FtpException
     */
    public static void main(String[] args) throws FtpException
    {
        // TODO Auto-generated method stub
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();

        // set the port of the listener
        factory.setPort(2121);
        // define SSL configuration
        SslConfigurationFactory ssl = new SslConfigurationFactory();
        ssl.setKeystoreFile(new File("D:\\liuys\\apache-ftpserver-1.1.1\\res\\ftp.jks"));
        ssl.setKeystorePassword("123456");

		ssl.setTruststoreFile(new File("D:\\liuys\\apache-ftpserver-1.1.1\\res\\ftptrustore.jks"));
		ssl.setKeystorePassword("123456");

        // set the SSL configuration for the listener
        factory.setSslConfiguration(ssl.createSslConfiguration());
        factory.setImplicitSsl(true);


        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("D:\\liuys\\apache-ftpserver-1.1.1\\res\\conf\\users.properties"));

        serverFactory.setUserManager(userManagerFactory.createUserManager());

        // start the server
        FtpServer server = serverFactory.createServer();

        server.start();
    }

}
