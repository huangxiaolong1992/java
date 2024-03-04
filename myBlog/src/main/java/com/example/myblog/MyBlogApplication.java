package com.example.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.example.myblog.mapper")
public class MyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
