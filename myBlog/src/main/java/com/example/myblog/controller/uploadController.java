package com.example.myblog.controller;

import com.example.myblog.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class uploadController {
    @Value("${upload.path}")
    private String filePath;

    @PostMapping("/upload")
    public R<String> upload(@RequestBody MultipartFile file) {
        String fileName = file.getOriginalFilename();

       //处理文件重名问题（当上传的文件同名，新上传的文件会将原文件覆盖），因此将UUID作为文件名，来解决该问题
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  //获取上传文件的后缀名
        fileName = UUID.randomUUID().toString() + suffixName;  //将UUID和后缀名拼接后的结果作为文件名

        //判断服务器中是否存在文件保存的目录，如果不存在，则创建目录
        File fileDir = new File(filePath);
        if(!fileDir.exists()){
            fileDir.mkdir();
        }

        String path = filePath + File.separator + fileName;  //File.separator表示路径的分隔符

        try {
            file.transferTo(new File(path));  //将文件保存到path目录下，
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  R.success("上传成功", "/watch/" + fileName);
    }
}
