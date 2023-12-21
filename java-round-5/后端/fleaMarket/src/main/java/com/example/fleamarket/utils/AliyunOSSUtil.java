package com.example.fleamarket.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class AliyunOSSUtil {
    //@Value("${spring.cloud.alicloud.oss.endpoint}")
    private static String  endpoint="oss-cn-beijing.aliyuncs.com";
    //@Value("${spring.cloud.alicloud.access-key}")
    private static String  accessKeyId="LTAI5t6WAjybmXMxC9L5a8ov";
    //@Value("${spring.cloud.alicloud.secret-key}")
    private static String  accessKeySecret="AKYs2rqmQSGuCe4pHOMXtBDHJ9cClM";
    //@Value("${spring.cloud.alicloud.oos.bucketName}")
    private static String  bucketName="flea--market";
    //@Value("${spring.cloud.alicloud.oos.filehost}")
    private static String  filedir="fleaMarket";


    /**
     *
     * 上传图片
     * @param file
     * @return
     */
    public  String uploadImg2Oss(MultipartFile file) {
        if (file.getSize() > 1024 * 1024 *20) {
            //RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_TOO_MAX);
            return "图片太大";
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            //RestResultGenerator.createSuccessResult(name);
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            //RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_UPLOAD);
            return "上传失败";
        }
    }

    /**
     * 上传图片获取fileUrl
     * @param instream
     * @param fileName
     * @return
     */
    private String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件

            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".xls") ||
                FilenameExtension.equalsIgnoreCase(".xlsx")) {
            return "application/vnd.ms-excel";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }

    /**
     * 获取图片路径
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (fileUrl!=null) {
            String[] split = fileUrl.split("/");
            String url =  this.getUrl(this.filedir + split[split.length - 1]);
            return url;
        }
        return null;
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


    /**
     * 多图片上传
     * @param fileList
     * @return
     */
    public String checkList(List<MultipartFile> fileList) {
        String  fileUrl = "";
        String  str = "";
        String  photoUrl = "";
        for(int i = 0;i< fileList.size();i++){
            fileUrl = uploadImg2Oss(fileList.get(i));
            str = getImgUrl(fileUrl);
            if(i == 0){
                photoUrl = str;
            }else {
                photoUrl += "," + str;
            }
        }
        return photoUrl.trim();
    }

    /**
     * 单个图片上传
     * @param file
     * @return
     */
    public  String upload(MultipartFile file){
        String fileUrl = uploadImg2Oss(file);
        String str = getImgUrl(fileUrl);
        return str.trim();
    }

}
