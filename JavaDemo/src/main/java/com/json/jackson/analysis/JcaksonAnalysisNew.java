package com.json.jackson.analysis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 读取json文件循环遍历
 * 　　* @date 2022/6/7
 */
@Slf4j
public class JcaksonAnalysisNew {

    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("D:/工作/恒泰/深智城/车联网/数据样例/龙华OBU-rsi.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();


        JcaksonAnalysisNew j = new JcaksonAnalysisNew();
        HashMap<String,Object> hashMap = (HashMap) j.Analysis(data,null);
        j.print(hashMap);
        System.out.println(hashMap.get("data"));
    }

    public void print(Object o){
        if(o instanceof List){
            ((ArrayList<Object>) o).forEach(l->
                    print(l)
            );
        }else if(o instanceof Map){
            ((Map<String,Object>) o).forEach((k,v)->{
                if(v instanceof List){
                    ((ArrayList<Object>) v).forEach(l->
                        print(l)
                    );
                }else if(v instanceof Map){
                    print(v);
                }else{
                    System.out.println("key:"+k+",value:"+v);
                }
            });
        }
    }

    public Map Analysis(String data, String parentName){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(data);
            Map result = new HashMap<String, Object>();
            if (json.isArray()) {
                json.forEach(j -> forEachRen(j, result, parentName));
            } else {
                forEachRen(json, result, parentName);
            }
            return result;
        }catch (JsonProcessingException je){
            log.error(je.toString());
            for (StackTraceElement s : je.getStackTrace()) {
                log.error(s.toString());
            }
        }
        return null;
    }

    //————————————————————————————————————————————————————————————————————————————————————————————
    //添加父节点名字(可稍微重名节点导致覆盖的问题)

    /**
     * @param data       json对象
     * @param result     封装数据的MAP结果集
     * @param parentKeyName 需要添加的key名称前缀
     * @return 返回封装数据的MAP结果集
     */
    public Map forEachRen(JsonNode data, Map<String, Object> result, String parentKeyName) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentKeyName)) keyName.append(parentKeyName).append("_");
            if (entry.getValue().isObject()) {
                //子对象处理
                forEachRen(entry.getValue(), result, keyName.append(entry.getKey()).toString());
            } else if (entry.getValue().isArray()) {
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d -> {
                    StringBuilder listKeyName = new StringBuilder(keyName.toString());
                    forEachRen(d, list, listKeyName.append(entry.getKey()).toString());
                });
                //list值放入map
                result.put(keyName.append(entry.getKey()).toString(), list);
            } else {
                //json值放入map
                result.put(keyName.append(entry.getKey()).toString(), entry.getValue().asText());
                log.info("{}:{},类型:{}", entry.getKey(), entry.getValue().asText(), entry.getValue().getNodeType());
            }
        }
        return result;
    }

    /**
     * @param data       json对象
     * @param result     result 需要封装数据进去的List结果集，将json解析添加在list最后一个节点
     * @param parentKeyName 需要添加的key名称前缀
     * @return 返回封装数据的List结果集
     */
    public List forEachRen(JsonNode data, List result, String parentKeyName) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        Map<String, Object> map = new HashMap<>();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentKeyName)) keyName.append(parentKeyName).append("_");
            if (entry.getValue().isObject()) {
                //子对象处理
                forEachRen(entry.getValue(), map, keyName.append(entry.getKey()).toString());
            } else if (entry.getValue().isArray()) {
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d -> {
                    StringBuilder listKeyName = new StringBuilder();
                    if (ObjectUtils.isNotEmpty(parentKeyName)) listKeyName.append(parentKeyName).append("_");
                    forEachRen(d, list, listKeyName.append(entry.getKey()).toString());
                });
                //list放入map
                map.put(keyName.append(entry.getKey()).toString(), list);
            } else {
                //json值放入map
                map.put(keyName.append(entry.getKey()).toString(), entry.getValue().asText());
                log.info("{}:{},类型:{}", entry.getKey(), entry.getValue().asText(), entry.getValue().getNodeType());
            }
        }
        result.add(map);
        return result;
    }

}
