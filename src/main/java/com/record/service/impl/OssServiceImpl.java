package com.record.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.record.service.OssService;
import com.record.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileImage(MultipartFile file) {
        // 工具类取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSS实例
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;

            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName = dataPath + fileName;

            ossClient.putObject(bucketName,fileName,inputStream);
            ossClient.shutdown();
            // 拼接字符串用于存储
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            return null;
        }
    }
}
