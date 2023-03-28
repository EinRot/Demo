package com.stream;

import java.util.stream.IntStream;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/7/8
 *
 */
public class IntStreamDemo {
    public static void main(String[] args) {
        IntStream.range(0,5).forEach(i-> System.out.println(i));
    }
}
