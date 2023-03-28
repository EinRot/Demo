package com.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 读取json文件循环遍历
 * 　　* @date 2022/6/7
 *
 */
public class JsonFile {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("D:\\工作\\恒泰\\深智城\\车联网\\数据样例\\龙华OBU-warn.json");
//        Reader reader = new FileReader("D:\\迅雷下载\\DwnlData\\截图\\test1.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.readTree(data);
        System.out.println(msg.get("warning").toString());
        JsonNode json = mapper.readTree(mapper.readTree(data).get("warning").asText());
        System.out.println(json.toString());
        Map<String, Object> valueList = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = json.fields();
        while (fields.hasNext()){
            Map.Entry<String, JsonNode> str = fields.next();
            valueList.put(str.getKey(),str.getValue().asText());
        }
        System.out.println(valueList);
    }

}
