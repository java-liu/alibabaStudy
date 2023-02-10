package com.ljava.minio;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Data
@Component
@Slf4j
//@ConfigurationProperties(prefix = "minio")
public class MinIOConfig {
    private String endpoint = "http://47.94.104.204";
    private String endpoint1 = "http://182.92.239.98/minio204";
    private int port = 9001;
    private String accessKey = "7SdP2L8LjmWlUomM";

    private String secretKey = "nj1DRrPDHKJOy4axxcBAaPZd9gPuh12B";

    // 如果是true，则用的是https而不是http,默认值是true
    private Boolean secure = false;

    // 默认存储桶
    private String bucketName = "terminal";
    private X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    private SSLSocketFactory sslSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] {x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public MinioClient getMinioClient() throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置超时时间
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("182.92.239.98", 9001)))
                .proxyAuthenticator((route, response) -> {
                    String credential = Credentials.basic("admin", "zzhl_666");
                    return response.request().newBuilder()
                            .header("Proxy-Authorization", credential)
                            .build();
                });
        OkHttpClient httpClient = new OkHttpClient();


        final String username = "7SdP2L8LjmWlUomM";
        final String password = "nj1DRrPDHKJOy4axxcBAaPZd9gPuh12B";
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("123.56.46.142", 3128));
        //Proxy proxy1 = new Proxy(Pro)


        Authenticator proxyAuthenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        };


        httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)//是否开启缓存
                .connectionPool(new ConnectionPool())//连接池
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .proxy(proxy)
                .proxyAuthenticator(proxyAuthenticator)
                .authenticator(proxyAuthenticator)
                .retryOnConnectionFailure(true)
                .build();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Response response = httpClient.newCall(request).execute();


        ResponseBody responseBody = response.peekBody(1024 * 1024);

        /*log.info(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                response.headers()));*/
        log.info(responseBody.string() + "" + response.headers());
        System.out.println(responseBody.toString());
        System.out.println("=======");
        MinioClient minioClient =
                MinioClient.builder().httpClient(httpClient)
                        .endpoint(endpoint,port, secure)
                        .credentials(accessKey, secretKey)
                        .build();
        return minioClient;
    }
}
