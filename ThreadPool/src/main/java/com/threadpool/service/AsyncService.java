package com.threadpool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AsyncService {

    public AsyncService(){
        for(int i = 0;i<10000;i++){
            cpuConsume();
        }
    }

    @Async("asyncExecutor")
    public void executeAsync(){
        int id = new Random().nextInt(100);
        log.info("{} start",id);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} end",id);
    }

    @Async("asyncExecutor")
    public void cpuConsume(){
        try {
            while (true){
                for(int i = 0;i<1000;i++){
                    long res = i*i*i;
                    System.out.println(res);
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}