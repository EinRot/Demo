package dds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 多数据源测试项目
 * 　　* @date 2022/3/28
 */
@EnableScheduling //开启定时任务
@SpringBootApplication
public class DDSApplication {
    public static void main(String[] args)  {
        SpringApplication.run(DDSApplication.class, args);
    }
}
