package com.json.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.json.JacksonMapper;
import lombok.Data;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/7/26
 *
 */
@Data
public class DtoBean {
    @JsonDeserialize(using = DemoDeserializer.class)
    @JsonSerialize(using = DemoSerializer.class)
    private Long time;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        DtoBean dtoBean = new DtoBean();
        dtoBean.setTime(1111l);
        String s = o.writeValueAsString(dtoBean);
        System.out.println(s);
        DtoBean d = o.readValue(s,DtoBean.class);
        System.out.println(d);
    }
}
