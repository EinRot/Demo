package com.json;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO json转换测试
 * 　　* @date 2021/12/1
 *
 */
public class JsonListDemo {
    public static void main(String[] args) {
        List<String> list=null;
        try {
            list = JacksonMapper.instance.objectMapper.readValue("[\"admin\",\"get\"]", ArrayList.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println((ObjectUtils.isEmpty(list)?"": JSONObject.toJSON(list).toString()));
        try {
            JsonNode jsonNode = JacksonMapper.instance.objectMapper.readTree("{\"a\":\"a\",\"b\":\"b\",\"c\":\"c\"}");
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()){
                Map.Entry<String, JsonNode> str = fields.next();
                System.out.println(str.getKey());
            }
            //对象转JSON字符串
            String strJson =  JacksonMapper.instance.objectMapper.writeValueAsString(list);
            System.out.println(strJson);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
