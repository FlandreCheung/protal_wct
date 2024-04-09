package com.record.service;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadFileImage(MultipartFile file);
}
