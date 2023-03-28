package com.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/5/27
 *
 *///启用异步方法执行功能
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class,args);
    }
}
