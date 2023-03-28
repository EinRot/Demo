package com.bat;

import java.util.Random;

//模拟龟兔赛跑
public class Race implements Runnable{

    private static String winner;

    @Override
    public void run() {
        Random random = new Random();
        int time = random.nextInt(100);

        for (int i = 0; i <= 100; i++) {
            //模拟兔子睡觉
            if (Thread.currentThread().getName().equals("兔子") && i % time == 0) {
                try {
                    System.out.println(Thread.currentThread().getName()+"睡觉");
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //判断比赛是否结束
            boolean flag = gameOver(i);
            //如果比赛结束了就停止程序
            if (flag){
                break;
            }

            System.out.println(Thread.currentThread().getName() + "-->跑了" + i + "步");
        }
    }

    //判断是否完成比赛
    private boolean gameOver(int steps) {
        if (winner != null) {//已经存在胜利者
            return true;
        }else {
            if (steps>=100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}