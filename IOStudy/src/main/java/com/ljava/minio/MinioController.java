package com.ljava.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.netty.util.concurrent.CompleteFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {


    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public void uploadFile() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        long start = System.currentTimeMillis();
        ObjectWriteResponse response = minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("terminal")
                        .object("options_1111.zip")
                        .filename("/Users/liuys/Downloads/98五笔单字查询.html")
                        .build());
        System.out.println(response.etag() + response.versionId());
        long end  = System.currentTimeMillis();
        System.out.println("用时:("+ (end -start ) + ")");

    }
    @PostMapping("/upload2")
    public void uploadFile2(@RequestParam MultipartFile file, HttpServletRequest request) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //给下一节点文件服务器发送文件
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://47.94.104.204", 9001, false)
                        .credentials("zzhlminioadmin", "zzhlminioadmin")
                        .build();
        ObjectWriteResponse terminal = minioClient.putObject(PutObjectArgs.builder()
                .bucket("test")
                .object("add.json")
                .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                .build());
        System.out.println("文件上传完成" + terminal.versionId() + terminal.etag());
    }

    @GetMapping("/exists")
    public void ifExists() throws Exception{
        minioService.bucketExists("terminal");
    }

    @GetMapping("/getBucketList")
    public void getBucketList() throws Exception{
        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.creationDate() + ", " + bucket.name());
        }
    }

    @GetMapping("/downloadObjects")
    public void downloadObjects()throws Exception{
        long start = System.currentTimeMillis();
        File file = new File("/Users/liuys/work/downloadfile/options_installer.zip");
        if(file.exists()){
            System.out.println("文件已经存在");
            file.delete();
        }
        minioClient.downloadObject(
                DownloadObjectArgs.builder()
                        .bucket("terminal")
                        .object("options_installer.zip")
                        .filename("/Users/liuys/work/downloadfile/options_installer.zip")
                        .build());
        long end  = System.currentTimeMillis();
        long tm = end - start;
        System.out.println("用时:("+ (end -start ) + ")");

    }

    @GetMapping("/getStat")
    public void getStat(HttpServletRequest request) throws Exception{
        try {
            String path = "http://iframe.ip138.com/ic.asp";// 要获得html页面内容的地址

            URL url = new URL(path);// 创建url对象

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接

            conn.setRequestProperty("contentType", "GBK"); // 设置url中文参数编码

            conn.setConnectTimeout(5 * 1000);// 请求的时间

            conn.setRequestMethod("GET");// 请求方式

            InputStream inStream = conn.getInputStream();
            // readLesoSysXML(inStream);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    inStream, "GBK"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            // 读取获取到内容的最后一行,写入
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String str = buffer.toString();
            String ipString1 = str.substring(str.indexOf("["));
            // 获取你的IP是中间的[182.149.82.50]内容
            String ipsString2 = ipString1.substring(ipString1.indexOf("[") + 1,
                    ipString1.lastIndexOf("]"));
            //获取当前IP地址所在地址
      /* String ipsString3=ipString1.substring(ipString1.indexOf(": "),ipString1.lastIndexOf("</center>"));
         System.err.println(ipsString3);*/

            // 返回公网IP值
            //return ipsString2;
            System.out.println(ipsString2);
        } catch (Exception e) {
            System.out.println("获取公网IP连接超时");
            //return "连接超时";
        }




    String ipAddress = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String benji = "127.0.0.1";
            String bj = "0:0:0:0:0:0:0:1";
            if (benji.equals(ipAddress) || bj.equals(ipAddress)) {
                ///根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(inet != null){
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        ///对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        int i = 15;
        String s = ",";
        if (ipAddress != null && ipAddress.length() > i) {
            if (ipAddress.indexOf(s) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        System.out.println(ipAddress);
        //return ipAddress;

        //minioService.makeBucket("");
        StatObjectResponse objectStat = null;
        try{
            objectStat  =
                    minioClient.statObject(
                            StatObjectArgs.builder().bucket("terminal").object("options_installer.zip").build());
        }catch (Exception e){
            objectStat = null;
        }
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->{
            for (int j = 0; j < 2000; j++) {
                System.out.println(j);
                int b = j/0;
            }
            return "true";
        });
        CompletableFuture<Integer> future1 = future.thenApplyAsync(result -> {
            System.out.println("===============");
           return 1;
        }).exceptionally(error -> {
            System.out.println(1);
            error.printStackTrace();
            return 2;
        });
    }

    public static void main(String[] args) {
        Integer a = null;
        try{
            int i = 2/12;
            a = 1;
        }catch (Exception e){
            a = null;
            System.out.println("出现异常"+ e.getMessage());
        }
        System.out.println("可以执行");
        if(a != null){
            System.out.println("执行了1");
        }else{
            System.out.println("执行了2");
        }
    }
}
