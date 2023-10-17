package com.smartedhub_server;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.smartedhub_server.mapper")
@EnableChatGPT
public class SmartEdHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEdHubServerApplication.class, args);
    }

}
