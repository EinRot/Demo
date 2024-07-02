package com.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 报错行数打印
 * 　　* @date 2022/7/5
 *
 */
@Slf4j
public class ExceptionPrint {
    public static void main(String[] args) {
        try{
            InputStream i = null;
            i.read();
        }catch (Exception e){
            log.info(e.getMessage());
            log.info(e.getClass().getName());
            log.info(e.toString());
            for(StackTraceElement s : e.getStackTrace()){
                log.info(s.toString());
            }
        }
    }
}
