package com.date;

import java.sql.Date;
import java.sql.Timestamp;
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


        //时间戳转字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(1729754640000L);
        String dateString = sdf.format(timestamp);
        System.out.println(dateString);
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(1729754640000L));
	}
}
