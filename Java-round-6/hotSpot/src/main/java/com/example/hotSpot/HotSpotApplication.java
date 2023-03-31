package com.example.hotSpot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(scanBasePackages = "com.example.hotSpot")
@MapperScan("com.example.hotSpot.dao")
@EnableCaching
public class HotSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotSpotApplication.class, args);
    }

}
