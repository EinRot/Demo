package com.mapstruct;

import java.time.LocalDateTime;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO Mapstruct对象转换测试
 * 　　* @date 2021/11/19
 *
 */
public class MapstructTest {
    public static void main(String[] args) {
        BerthDynamic bd = new BerthDynamic();
        System.out.println(bd.getStartRealTime()==null);
        bd.setCraftNo("123");
        bd.setCraftSite("123");
        bd.setEndRealTime(LocalDateTime.now());
        bd.setEndTime(LocalDateTime.now());
        bd.setRealTimeType("1");
        bd.setStartRealTime(LocalDateTime.now());
        bd.setStartTime(LocalDateTime.now());
        bd.setFlightId(123L);
        BerthMaster bm = MasterConverter.INSTANCE.berthToBerthConvert(bd);
        System.out.println(bm.toString());
    }
}
