package com.thread.countdown.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.TimeZone;

/**
  * @author EinIce
  * @description TODO Jackson序列工具
  * @date 2022/5/7
  */
public enum JacksonMapper {
    instance;
    public final DateTimeFormatter FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmssZZ");
    public final DateTimeFormatter FROMAT_DATETIME_YYYY_MM_DD_HH_MM = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    public final DateTimeFormatter FROMAT_DATETIME_YYYY_MM_DD_HH_MM_SS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public final DateTimeFormatter FROMAT_DATETIME_YYYYMMDDHH = DateTimeFormat.forPattern("yyyyMMddHH");
    public final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());

    JacksonMapper() {
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        objectMapper.enable(MapperFeature.USE_GETTERS_AS_SETTERS);
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
    }
}
