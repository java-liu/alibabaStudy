package com.ljava.somemethod.hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HdfsLineCount {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 配置信息
        Configuration conf = new Configuration();
        String hdfsPath = "/opt/hdfs/data"; // HDFS 路径
        conf.set("fs.defaultFS", "hdfs://59.110.218.80:9000");
        try {
            // 配置 HDFS 连接
            conf.set("fs.hdfs.impl.disable.cache", "true");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            conf.addResource(new Path("core-site.xml"));
            conf.addResource(new Path("hdfs-site.xml"));
            // 创建 HDFS 文件系统对象
            FileSystem fs = FileSystem.get(new URI(hdfsPath), conf);
            // 获取文件或目录的状态信息
            FileStatus[] fileStatuses = fs.listStatus(new Path(hdfsPath));
            int fileCount = 0;
            for (FileStatus status : fileStatuses) {
                if (status.isFile()) {
                    fileCount++;
                }
            }
            System.out.println("文件个数 " + hdfsPath + ": " + fileCount);
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentTime = LocalDateTime.now().format(sdf);
            String examCardNumber = "050115405350";
            String name = "刘永山";
            // 打印信息
            System.out.println("程序执行时间：" + currentTime);
            System.out.println("准考证号：" + examCardNumber);
            System.out.println("姓名：" + name);
            // 遍历文件并输出文件信息
            for (FileStatus status : fileStatuses) {
                System.out.println("文件路径: " + status.getPath());
                System.out.println("文件大小: " + status.getLen());
                System.out.println("副本数: " + status.getReplication());
                System.out.println("块大小: " + status.getBlockSize());
                System.out.println("修改时间: " + status.getModificationTime());
                System.out.println("是否为目录: " + status.isDirectory());
                System.out.println("-------------------------");
            }
            // 关闭文件系统对象
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
