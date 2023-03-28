package com.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
  * @author EinIce
  * @description: TODO 反序列化处理DateTime格式
  * @date 2021/11/15
  */
public class StrToDateTimeUtil {
	private final static DateTimeFormatter FROMAT_yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd");
    private final static DateTimeFormatter FROMAT_yyyyMMddHH = DateTimeFormat.forPattern("yyyyMMddHH");
    private final static DateTimeFormatter FROMAT_yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private final static DateTimeFormatter FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmssZZ");
    private final static DateTimeFormatter FROMAT_1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final static DateTimeFormatter FROMAT_2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_S = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_SS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SS");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_SSS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final static DateTimeFormatter FROMAT_3 = DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM = DateTimeFormat.forPattern("yyyyMM");
    private final static DateTimeFormatter FROMAT_DATETIME_28 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
    private final static DateTimeFormatter FROMAT_DATETIME_29 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
    private final static DateTimeFormatter FROMAT_DATETIME_30 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS+08:00");
    private final static DateTimeFormatter FROMAT_DATETIME_31 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSS+08:00");
    private final static DateTimeFormatter FROMAT_DATETIME_32 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS+08:00");
    private final static DateTimeFormatter FROMAT_DATETIME_33 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS+08:00");
    
    public static DateTime strToDateTime(String str){
        switch (str.length()) {
        	case 6:
        		return DateTime.parse(str, FROMAT_DATETIME_yyyy_MM);
            case 8:
                return DateTime.parse(str, FROMAT_yyyyMMdd);
            case 10:
                return DateTime.parse(str, FROMAT_yyyyMMddHH);
            case 14:
                return DateTime.parse(str, FROMAT_yyyyMMddHHmmss);
            case 17:
            	return DateTime.parse(str,FROMAT_3);
            case 18:
                return DateTime.parse(str, FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ);
            case 19:
                if (str.contains("T")) {
                    return DateTime.parse(str, FROMAT_1);
                } else {
                    return DateTime.parse(str, FROMAT_2);
                }
            case 21:
            	return  DateTime.parse(str, FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_S);
            case 22:
            	return  DateTime.parse(str, FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_SS);
            case 23:
            	return  DateTime.parse(str, FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_SSS);
            case 28:
            	return	DateTime.parse(str, FROMAT_DATETIME_28);
            case 29:
            	return	DateTime.parse(str, FROMAT_DATETIME_29);
            case 30:
            	return	DateTime.parse(str, FROMAT_DATETIME_30);
            case 31:
            	return  DateTime.parse(str, FROMAT_DATETIME_31);
            case 32:
            	return  DateTime.parse(str, FROMAT_DATETIME_32);
            case 33:
            	return  DateTime.parse(str, FROMAT_DATETIME_33);
            default:
                return DateTime.parse(str, FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ);
        }
    }
}
