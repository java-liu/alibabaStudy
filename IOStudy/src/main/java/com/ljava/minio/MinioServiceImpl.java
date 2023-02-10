package com.ljava.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioServiceImpl implements MinioService{

    @Autowired
    private MinioClient minioClient;

    @Override
    public boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("terminal").build());
        if (found) {
            System.out.println("my-bucketname exists");
        } else {
            System.out.println("my-bucketname does not exist");
        }
        return found;
    }

    @Override
    public void makeBucket(String bucketName) {
        try{
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void putObject(String bucketName, String objectName, String filename) {

    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, String contentType) {

    }

    @Override
    public void putObject(String bucketName, MultipartFile multipartFile, String filename) {

    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return false;
    }

    @Override
    public void downloadFile(String bucketName, String fileName, String originalName, HttpServletResponse response) {

    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return null;
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return null;
    }

    @Override
    public Iterable<Result<Item>> listObjects(String bucketName) {
        return null;
    }

    @Override
    public String presignedGetObject(String bucketName, String objectName, Integer expires) {
        return null;
    }
}
