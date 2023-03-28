package com.bat;

import java.io.InputStream;
import java.util.Random;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/5/26
 *
 */
public class BatReadThread implements Runnable{

    private InputStream in;

    public BatReadThread(InputStream in){
        this.in = in;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int time = random.nextInt(100);
        for (int i = 0; i <= 100; i++) {
            System.out.println(i % time);
        }
        System.out.println(time);
    }
    @Override
    public void run() {

    }
}
