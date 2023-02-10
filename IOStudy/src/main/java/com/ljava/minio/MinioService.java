package com.ljava.minio;

import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface MinioService {
    /**
     * 判断 bucket是否存在
     *
     * @param bucketName
     * @return
     */
    boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    void makeBucket(String bucketName);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param filename
     */
    void putObject(String bucketName, String objectName, String filename);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param stream
     */
    void putObject(String bucketName, String objectName, InputStream stream, String contentType);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param multipartFile
     */
    void putObject(String bucketName, MultipartFile multipartFile, String filename);

    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     */
    boolean removeObject(String bucketName,String objectName);

    /**
     * 下载文件
     *
     * @param fileName
     * @param originalName
     * @param response
     */
    void downloadFile(String bucketName, String fileName, String originalName, HttpServletResponse response);

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    String getObjectUrl(String bucketName,String objectName);




    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    InputStream getObject(String bucketName, String objectName);


    /**
     * 列出存储桶中所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     */
    Iterable<Result<Item>> listObjects(String bucketName);


    /**
     * 生成一个给HTTP GET请求用的presigned URL
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires 失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     */
    String presignedGetObject(String bucketName, String objectName, Integer expires);

}

