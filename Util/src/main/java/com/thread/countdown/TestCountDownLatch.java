package com.thread.countdown;

import com.json.fastjson.analysis.JsonAnalysis;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.CountDownLatch;

/**
  * 性能测试
  * @author EinIce
  * @description TODO 性能测试
  * @date 2022/7/8
  */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException, IOException {
        Reader reader = new FileReader("D:/data/垃圾场/test.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();
    	//创建一个CountDownLatch对象，并调用构造器将计数器设置为5
        CountDownLatch latch = new CountDownLatch(5);
        //创建一个LatchDemo对象，并将CountDownLatch的实例对象传入
        LatchDemo ld = new LatchDemo(latch,data);
		//记录开始时间
        long start = System.currentTimeMillis();
		//通过循环创建并启动五个线程
        for(int i = 0; i < 5; i++){
            new Thread(ld).start();
        }
        try {
        	//调用await()方法，阻塞主线程，当上述启动的5个分线程都执行完后，主线程才会被放行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //记录下结束时间
        long end = System.currentTimeMillis();
        System.out.println("消耗的时间为（毫秒）：" + (end - start));
    }
}

class LatchDemo implements Runnable{
	//用来存放一个闭锁
    private CountDownLatch latch;
    private String data;
    //构造器初始化
    public LatchDemo(CountDownLatch latch,String data){
        this.latch = latch;
        this.data = data;
    }
    @Override
    public void run() {
        try{
            System.out.println("建立线程");
            long threadStartTime = System.currentTimeMillis();
            //————————————————————————————————————————————————————————————
            for (int i = 0; i < 1000000; i++) {
                JsonAnalysis ja = new JsonAnalysis(false);
                ja.analysis(data);
            }
            //————————————————————————————————————————————————————————————
            long threadEndTime = System.currentTimeMillis();
            System.out.println("thread:《"+(threadEndTime-threadStartTime)+"》");
        }finally {
        	//将该代码放入finally中，确保这段代码一定能执行，否则主线程无法结束
            latch.countDown();	//让CountDownLatch计数器减1
        }
    }
}