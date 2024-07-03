package com.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/2 16:13
 **/
@SpringBootApplication
@ComponentScan(value = {"com.drive"})
public class BuildApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildApplication.class);
    }
}
