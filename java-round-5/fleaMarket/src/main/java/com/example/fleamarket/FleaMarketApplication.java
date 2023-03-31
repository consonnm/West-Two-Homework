package com.example.fleamarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.fleamarket")
@MapperScan("com.example.fleamarket.dao")
public class FleaMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleaMarketApplication.class, args);
    }

}
