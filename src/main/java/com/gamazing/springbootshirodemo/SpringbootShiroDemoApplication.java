package com.gamazing.springbootshirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gamazing.springbootshirodemo.dao")
public class SpringbootShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroDemoApplication.class, args);
    }

}
