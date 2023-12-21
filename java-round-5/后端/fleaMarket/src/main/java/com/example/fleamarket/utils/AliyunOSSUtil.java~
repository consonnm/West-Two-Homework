package com.example.fleamarket.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class AliyunOSSUtil {
    @Value("${oss.file.endpoint}")
    private static String  endpoint;
    @Value("${oos.file.keyId}")
    private static String  keyId;
    @Value("${oos.file.keySecret}")
    private static String  keySecret;
    @Value("${oos.file.bucketName}")
    private static String  bucketName;
    @Value("${oos.file.filehost}")
    private static String  filehost;


    public static String upload(MultipartFile file){

        //文件新路径
        String fileName = file.getOriginalFilename();
        OSSClient ossClient = new OSSClient(endpoint,keyId,keySecret);
        try {
            assert fileName != null;
            File newFile = new File(fileName);
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                assert os != null;
                os.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(new Date());

            //创建文件路径
            String fileUrl = filehost+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            // 上传到阿里云
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, newFile));
            if(null != result){
                return fileUrl;
            }
        }catch (OSSException | ClientException e){
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return null;
    }

}
