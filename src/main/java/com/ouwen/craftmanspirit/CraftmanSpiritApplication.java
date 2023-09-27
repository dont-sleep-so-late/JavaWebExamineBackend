package com.ouwen.craftmanspirit;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ouwen.*.mapper")
public class CraftmanSpiritApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftmanSpiritApplication.class, args);
    }

}
