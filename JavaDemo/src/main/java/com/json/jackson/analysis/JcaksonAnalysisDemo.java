package com.json.jackson.analysis;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 读取json文件循环遍历
 * 　　* @date 2022/6/7
 */
@Slf4j()
public class JcaksonAnalysisDemo {
    @Getter
    enum JsonAnalysisType{
        LIST(List.class),
        MAP(Map.class);
        JsonAnalysisType(Class clazz){
            this.clazz=clazz;
        }
        Class clazz;
    }
    //调整log打印日志级别
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();

        //测试
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        System.out.println(json.getNodeType());
        System.out.println(json);
        JcaksonAnalysisDemo jsonAnalysis = new JcaksonAnalysisDemo();

        Map<String, Object> result = new HashMap<>();
        result = jsonAnalysis.forEachRen(json, result, null);
        log.warn("带父名字Map结果:{}", result.toString());

//        Map<String, Object> result2 = new HashMap<>();
//        result2 = jsonAnalysis.forEach(json, result2);
//        log.warn("不带父名字Map结果:{}", result2.toString());

//        List<Object> list = new ArrayList<>();
//        list = jsonAnalysis.forEachRen(json, list, null);
//        log.warn("带父名字List结果:{}", list.toString());

//        List<Object> list2 = new ArrayList<>();
//        list2 = jsonAnalysis.forEach(json, list2);
//        log.warn("不带父名字List结果:{}", list2.toString());

        JcaksonAnalysisDemo j = new JcaksonAnalysisDemo();
        HashMap hashMap = (HashMap) j.Analysis(data,null,JsonAnalysisType.MAP);
        System.out.println(hashMap);
    }

    public Object Analysis(String data, String parentName,JsonAnalysisType jType) {
        Object result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(data);
            switch (jType){
                case MAP:
                    result = new HashMap<String, Object>();
                    if (json.isArray()) {
                        json.forEach(j -> forEachRen(j, (HashMap)result, parentName));
                    } else {
                        forEachRen(json, (HashMap)result, parentName);
                    }
                    break;
                case LIST:
                    result = new ArrayList<>();
                    if (json.isArray()) {
                        json.forEach(j -> forEachRen(j, (ArrayList)result, parentName));
                    } else {
                        forEachRen(json, (ArrayList)result, parentName);
                    }
                    break;
                default:
                    return null;
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
    //不加父节点名字(重名节点会导致覆盖)
    /**
     * @param data   json对象
     * @param result 封装数据的MAP结果集
     * @return 返回封装数据的MAP结果集
     */
    public Map forEach(JsonNode data, Map<String, Object> result) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getValue().isObject()) {
                //子对象处理
                forEach(entry.getValue(), result);
            } else if (entry.getValue().isArray()) {
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d ->
                        forEach(d, list)
                );
                result.put(entry.getKey(), list);
            } else {
                result.put(entry.getKey(), entry.getValue());
                log.info("{}:{},类型:{}", entry.getKey(), entry.getValue().asText(), entry.getValue().getNodeType());
            }
        }
        return result;
    }

    /**
     * @param data   json对象
     * @param result 需要封装数据进去的List结果集，将json解析添加在list最后一个节点
     * @return 返回封装数据的List结果集
     */
    public List forEach(JsonNode data, List result) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        Map<String, Object> map = new HashMap<>();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getValue().isObject()) {
                log.info("二级子对象节点:{}", entry.getKey());
                //子对象处理
//                map.put(entry.getKey(), entry.getValue());
                forEach(entry.getValue(), map);
            } else if (entry.getValue().isArray()) {
                log.info("二级数组节点:{}", entry.getKey());
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d ->
                        forEach(d, list)
                );
                map.put(entry.getKey(), list);
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        result.add(map);
        return result;
    }

    //————————————————————————————————————————————————————————————————————————————————————————————
    //添加父节点名字(可稍微重名节点导致覆盖的问题)

    /**
     * @param data       json对象
     * @param result     封装数据的MAP结果集
     * @param parentName 需要添加的key名称前缀
     * @return 返回封装数据的MAP结果集
     */
    public Map forEachRen(JsonNode data, Map<String, Object> result, String parentName) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentName)) keyName.append(parentName).append("_");
            if (entry.getValue().isObject()) {
                log.info("子对象节点:{}", entry.getKey());
                //子对象处理
                forEachRen(entry.getValue(), result, keyName.append(entry.getKey()).toString());
            } else if (entry.getValue().isArray()) {
                log.info("数组节点:{}", entry.getKey());
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d -> {
                    StringBuilder listKeyName = new StringBuilder(keyName.toString());
                    forEachRen(d, list, listKeyName.append(entry.getKey()).toString());
                });
                result.put(keyName.append(entry.getKey()).toString(), list);
            } else {
                result.put(keyName.append(entry.getKey()).toString(), entry.getValue());
                log.info("{}:{},类型:{}", entry.getKey(), entry.getValue().asText(), entry.getValue().getNodeType());
            }
        }
        return result;
    }

    /**
     * @param data       json对象
     * @param result     result 需要封装数据进去的List结果集，将json解析添加在list最后一个节点
     * @param parentName 需要添加的key名称前缀
     * @return 返回封装数据的List结果集
     */
    public List forEachRen(JsonNode data, List result, String parentName) {
        Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
        Map<String, Object> map = new HashMap<>();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentName)) keyName.append(parentName).append("_");
            if (entry.getValue().isObject()) {
                log.info("二级子对象节点:{}", entry.getKey());
                //子对象处理
//                map.put(entry.getKey(), entry.getValue());
                forEachRen(entry.getValue(), map, keyName.append(entry.getKey()).toString());
            } else if (entry.getValue().isArray()) {
                log.info("二级数组节点:{}", entry.getKey());
                //数组节点处理
                List list = new ArrayList();
                entry.getValue().forEach(d -> {
                    StringBuilder listKeyName = new StringBuilder();
                    if (ObjectUtils.isNotEmpty(parentName)) listKeyName.append(parentName).append("_");
                    forEachRen(d, list, listKeyName.append(entry.getKey()).toString());
                });
                map.put(keyName.append(entry.getKey()).toString(), list);
            } else {
                map.put(keyName.append(entry.getKey()).toString(), entry.getValue());
            }
        }
        result.add(map);
        return result;
    }

}
