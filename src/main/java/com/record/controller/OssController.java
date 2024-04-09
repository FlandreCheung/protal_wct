package com.record.controller;

import com.record.service.OssService;
import com.record.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "对象存储")
@RestController
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/oss/file")
    public Result uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileImage(file);
        return Result.success(url);
    }

    @PostMapping("/oss/saveUrl")
    public Result insertUrl(String url){
        return Result.success();
    }
}
