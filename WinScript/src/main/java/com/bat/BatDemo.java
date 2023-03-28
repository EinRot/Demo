package com.bat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO bat脚本测试
 * 　　* @date 2022/5/26
 *
 */
public class BatDemo {
    public static void main(String[] args) throws IOException {
        BatDemo batDemo = new BatDemo();
        batDemo.service();
    }

    public void service() throws IOException {
        //判断文件是否存在
        String batPath = "D:/Projects/ideaProjects/EinIce/DEMO/WinScript/src/main/resources/script/start.bat";
        File batFile = new File(batPath);
        boolean batFileExist = batFile.exists();
        System.out.println("batFileExist:" + batFileExist);
        if (batFileExist) {
            //文件存在则继续
            Process exec = Runtime.getRuntime().exec(batPath);

            StringBuilder sb = new StringBuilder();
            InputStream inputStream = exec.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line + "\n");
            }
            System.out.println(sb);
            OutputStream outputStream = exec.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()){
                String str = sc.nextLine();
                outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    /**执行本地bat文件*/
    /*
    cmd /c dir 是执行完dir命令后关闭命令窗口.
    cmd /k dir 是执行完dir命令后不关闭命令窗口.
    cmd /c start dir 会打开一个新窗口后执行dir指令, 原窗口会关闭.
    cmd /k start dir 会打开一个新窗口后执行dir指令, 原窗口不会关闭.*/
    public void Execute1() throws IOException {
        String command = "cmd /k start D:/Projects/ideaProjects/EinIce/DEMO/WinScript/src/main/resources/script/start.bat";
        //重启ACMD爬虫程序
        Runtime.getRuntime().exec(command);
    }
}
