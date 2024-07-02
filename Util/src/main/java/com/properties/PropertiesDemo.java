package com.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 配置文件读取测试
 * 　　* @date 2022/4/20
 *
 */
public class PropertiesDemo {
    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("user.dir"));
        Properties properties = new Properties();

        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("JavaDemo/src/main/resources/application.properties"));
        properties.load(bufferedReader);
        System.out.println(properties.getProperty("spring.profiles.active"));
    }
}
