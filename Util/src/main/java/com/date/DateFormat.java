package com.date;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author chenjunhong
 * @description: 日期格式化测试
 * @date 2022/10/21
 */
public class DateFormat {
    public static void main(String[] args) throws ParseException {
        Date date = new Date(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(date.toString());
        System.out.println(new StringBuilder(new SimpleDateFormat("yyyy-MM-dd 12:00:00").format(date)));
    
	}
}
