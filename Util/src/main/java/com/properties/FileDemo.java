package com.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 文件路径读取
 * 　　* @date 2022/4/24
 *
 */
public class FileDemo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources/application.properties");
        //输入的路径（包含文件名）
        System.out.println(file.getPath());
        System.out.println(file.exists());
        //输入的路径（不包含文件名）
        System.out.println(file.getParentFile());
        //绝对路径
        System.out.println(file.getAbsolutePath());
        //运行路径
        System.out.println(System.getProperty("user.dir"));
    }
}
