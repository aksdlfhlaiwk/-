package com.shenzhenxinxizhiye.web_ai_project02.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shenzhenxinxizhiye.web_ai_project02.pojo.Result;
import com.shenzhenxinxizhiye.web_ai_project02.utils.AliyunOSSOperator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UploadController {

    private final AliyunOSSOperator aliyunOSSOperator;

    public UploadController(AliyunOSSOperator aliyunOSSOperator) {
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    /*
     * 这种是往本地磁盘中存储的
     * 
     */
    // @PostMapping("/upload")
    // public Result Upload(String name, Integer age, MultipartFile file) throws
    // IllegalStateException, IOException {
    // log.info("{},{},{}", name, age, file);
    // // 保存文件
    // String originalFileName = file.getOriginalFilename();

    // if (originalFileName == null || originalFileName.isEmpty()) {
    // return Result.error("上传文件名不能为空"); // 假设你有 Result 类
    // }
    // String extension =
    // originalFileName.substring(originalFileName.lastIndexOf("."));

    // String newFileName = UUID.randomUUID().toString() + extension;
    // file.transferTo(new File("D:/images/employeeImages/" + newFileName));

    // return Result.success();
    // }

    /*
     * 存储在阿里云中
     */
    @PostMapping("/upload")
    public Result Upload(MultipartFile file) throws Exception {

        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }

}
