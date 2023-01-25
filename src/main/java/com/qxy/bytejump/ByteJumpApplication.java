package com.qxy.bytejump;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.qxy.bytejump.mapper")
public class ByteJumpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteJumpApplication.class, args);
    }

}
