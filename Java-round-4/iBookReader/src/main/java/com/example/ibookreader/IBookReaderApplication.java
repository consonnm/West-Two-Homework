package com.example.ibookreader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.ibookreader")
@MapperScan("com.example.ibookreader.dao")
public class IBookReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IBookReaderApplication.class, args);
    }

}
