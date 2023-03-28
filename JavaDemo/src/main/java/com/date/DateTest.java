package com.date;

import org.joda.time.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 日期测试
 * 　　* @date 2021/11/12
 *
 */
public class DateTest {
    public static void main(String[] args) throws ParseException, IOException {
        //格式化
        DateTime date = StrToDateTimeUtil.strToDateTime("2021-10-14 11:15:00");
        System.out.println("StrToDateTimeUtil:"+date.toString("yyyy-MM-dd"));
        //时间算数
        LocalDateTime now = LocalDateTime.parse("2021-11-15T15:30:38");
        LocalDateTime now2 = LocalDateTime.now();
        Duration duration = Duration.between(now,now2);
        System.out.println("duration:"+duration);
        long hours = duration.toHours();//相差的小时数
        System.out.println("duration.toHours:"+hours);
        //转换格式
        SimpleDateFormat FORMATTER_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        System.out.println("SimpleDateFormat:"+FORMATTER_YYYYMMDD.parse("20211025"));
        //正则
        if("arrive".matches("arrive|close")){
            System.out.println("matches:"+1);
        }
        //英文时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyy",Locale.UK);
        Date d = new Date();
        System.out.println("toUpperCase:"+formatter.format(d).toUpperCase());
        //英文时间格式
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMMyy", Locale.UK);
        LocalDateTime d2 = LocalDateTime.now();
        System.out.println("format:"+d2.format(formatter2));
        //
        System.out.println("length:"+"1636601380583".length());
        Long s = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        Long s2 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("toInstant:"+s);//时间戳
        System.out.println("toInstant:"+s2);//时间戳
        LocalDateTime p=LocalDateTime.ofEpochSecond(s, 0, ZoneOffset.ofHours(8));
        LocalDateTime p2=LocalDateTime.ofEpochSecond(s, 0, ZoneOffset.ofHours(8));
        System.out.println("ofEpochSecond:"+p);

        Instant instant = Instant.ofEpochMilli(1651219826469L);
        LocalDateTime tp =LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(8));
        LocalDateTime tp2 = Instant.ofEpochMilli(1651219826469L).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println(tp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(tp2);

        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d3 = LocalDateTime.now();
        System.out.println(d3.format(formatter3));
    }
}
