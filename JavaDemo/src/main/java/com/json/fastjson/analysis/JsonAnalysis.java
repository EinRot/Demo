package com.json.fastjson.analysis;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json通用解析类
 *
 * @author EinIce
 * @description: TODO 解析Json工具，返回Map、List类型
 * @date 2022/7/6
 */
@Slf4j
public class JsonAnalysis {

    /**
     * 拼接两个字段的连接符
     */
    String linkFlag = ">>";
    /**
     * 打印解析日志开关
     */
    Boolean printLog = false;

    public JsonAnalysis() {
    }

    /**
     * @param printLog 是否需要打印解析日志
     */
    public JsonAnalysis(Boolean printLog) {
        this.printLog = printLog;
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
        StringBuilder buffer = new StringBuilder();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        //读取的json文件
        String data = buffer.toString();
        JsonAnalysis ja = new JsonAnalysis(false);
        //调用解析
        Map<String, Object> result = ja.analysis(data);
        //打印map结果
//        ja.printMap(result);
        ja.printMapToJson(result);
    }

    /**
     * json解析功能调用入口
     * @param data json的字符串
     * @return 返回Map结果集
     */
    public Map<String, Object> analysis(String data) {
        try {
            JSONObject json = JSON.parseObject(data);
            Map<String, Object> result = new HashMap<>();
            return forEachRen(json, result, null, new SinkBottom());
        } catch (JSONException je) {
            log.error("类型错误，将尝试JSONArray类型");
            try {
                JSONArray json = JSON.parseArray(data);
                List<Object> resultList = new ArrayList<>();
                for (Object obj : json) {
                    Map<String, Object> result = new HashMap<>();
                    resultList.add(forEachRen((JSONObject) obj, result, null, new SinkBottom()));
                }
            } catch (Exception e) {
                log.error("{}:{}", je.getClass().getName(), je.getMessage());
                for (StackTraceElement s : e.getStackTrace()) {
                    log.error(s.toString());
                }
            }
        }
        return null;
    }

    /**
     * @param data          JSON数据源
     * @param result        返回的结果容器
     * @param parentKeyName 添加前缀名字
     * @param sinkBottom    沉底计算标志
     * @return 返回result结果容器
     */
    public Map<String, Object> forEachRen(JSONObject data, Map<String, Object> result, String parentKeyName, SinkBottom sinkBottom) {
        //循环json属性放入map
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentKeyName)) keyName.append(parentKeyName).append(linkFlag);
            //解析嵌套对象、迭代
            if (entry.getValue() instanceof JSONObject) {
                forEachRen((JSONObject) entry.getValue(), result, keyName.append(entry.getKey()).toString(), sinkBottom);
            } else if (entry.getValue() instanceof JSONArray) {
                sinkBottom.upIntoStatus();
                //解析对象数组
                //是否是对象数组标志符
                boolean isObjList = true;
                List<Object> list = new ArrayList<>();
                for (Object j : (JSONArray) entry.getValue()) {
                    StringBuilder listKeyName = new StringBuilder(keyName.toString());
                    listKeyName.append(entry.getKey());
                    if (!(j instanceof JSONObject)) {
                        //添加纯数组到最外层(仅添加父节点为非数组结构)
                        try {
                            result.put(listKeyName.toString(), ((JSONArray) entry.getValue()).toList(j.getClass()));
                        } catch (Exception e) {
                            log.error("放入数组失败：《{}》,改为直接存入", listKeyName);
                            for (StackTraceElement s : e.getStackTrace()) {
                                log.error(s.toString());
                            }
                            result.put(listKeyName.toString(), entry.getValue());
                        }
                        isObjList = false;
                        if (printLog)
                            log.info("解析：《{}》:《{}》,类型:《{}》", listKeyName, entry.getValue(), entry.getValue().getClass().getSimpleName());
                        break;
                    } else {
                        //解析数组里的对象、迭代
                        forEachRen((JSONObject) j, result, list, listKeyName.toString(), sinkBottom);
                    }
                }
                //纯数组不再添加，防止覆盖
                if (isObjList) {
                    keyName.append(entry.getKey());
                    result.put(keyName.toString(), list);
                }
                sinkBottom.upOutStatus();
            } else {
                keyName.append(entry.getKey());
                //json值放入map
                result.put(keyName.toString(), entry.getValue());
                if (ObjectUtils.isNotEmpty(entry.getValue())) {
                    if (printLog)
                        log.info("解析：《{}》:《{}》,类型:《{}》", keyName, entry.getValue(), entry.getValue().getClass().getSimpleName());
                }else if (printLog) log.info("解析：《{}》:《{}》,类型:《{}》", keyName, entry.getValue(), null);
            }
        }
        return result;
    }

    /**
     * @param data          JSON数据源
     * @param result        返回的结果容器
     * @param resultList    拼凑的resultList结果集
     * @param parentKeyName 添加前缀名字
     * @param sinkBottom    沉底计算标志
     * @return 返回result结果容器
     */
    public List<Object> forEachRen(JSONObject data, Map<String, Object> result, List<Object> resultList, String parentKeyName, SinkBottom sinkBottom) {
        Map<String, Object> nodeMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            StringBuilder keyName = new StringBuilder();
            if (ObjectUtils.isNotEmpty(parentKeyName)) keyName.append(parentKeyName).append(linkFlag);
            if (entry.getValue() instanceof JSONObject) {
                //子对象处理
                forEachRen((JSONObject) entry.getValue(), nodeMap, entry.getKey(), sinkBottom);
            } else if (entry.getValue() instanceof JSONArray) {
                sinkBottom.upIntoStatus();
                //解析对象数组
                //是否是对象数组标志符
                boolean isList = true;
                List<Object> nodeList = new ArrayList<>();
                for (Object j : (JSONArray) entry.getValue()) {
                    StringBuilder listKeyName = new StringBuilder(keyName.toString());
                    listKeyName.append(entry.getKey());
                    if (!(j instanceof JSONObject)) {
                        try {
                            nodeMap.put(entry.getKey(), ((JSONArray) entry.getValue()).toList(j.getClass()));
                        } catch (Exception e) {
                            log.error("放入数组失败：《{}》,改为直接存入", listKeyName);
                            for (StackTraceElement s : e.getStackTrace()) {
                                log.error(s.toString());
                            }
                            nodeMap.put(entry.getKey(), entry.getValue());
                        }
                        //放入非key-value值到最外层
                        isList = false;
                        if (printLog)
                            log.info("解析：《{}》:《{}》,类型:《{}》", listKeyName, entry.getValue(), entry.getValue().getClass().getSimpleName());
                        break;
                    } else {
                        //解析数组里的对象、迭代
                        forEachRen((JSONObject) j, result, nodeList, listKeyName.toString(), sinkBottom);
                    }
                }
                //纯数组不再添加，防止覆盖
                if (isList) {
                    StringBuilder listKeyName = new StringBuilder(keyName.toString());
                    listKeyName.append(entry.getKey());
                    nodeMap.put(entry.getKey(), nodeList);
                    //递归沉底，把数据添加到Map最外层
                    if (sinkBottom.isIntoStatus()) {
                        result.put(listKeyName.toString(), nodeList);
//                        if(result.containsKey(listKeyName.toString()))
//                            result.put(listKeyName.toString(), null);
//                        else
//                            result.put(listKeyName.toString(), nodeList);
                    }
                }
                sinkBottom.upOutStatus();
            } else {
                keyName.append(entry.getKey());
                //json值放入map
                nodeMap.put(entry.getKey(), entry.getValue());
                if (ObjectUtils.isNotEmpty(entry.getValue())) {
                    if (printLog)
                        log.info("解析：《{}》:《{}》,类型:《{}》", keyName, entry.getValue(), entry.getValue().getClass().getSimpleName());
                }else if (printLog) log.info("解析：《{}》:《{}》,类型:《{}》", keyName, entry.getValue(), null);
            }
        }
        resultList.add(nodeMap);
        return resultList;
    }


    /**
     * 打印JSONObject对象
     *
     * @param jsonObject 放入的JSONObject
     */
    public void printJsonObject(JSONObject jsonObject) {
        jsonObject.forEach((k, v) -> {
            if (v instanceof JSONArray) {
                ((JSONArray) v).forEach(j -> {
                    ((JSONObject) j).forEach((a, b) -> {
                        log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》", v.getClass().getSimpleName(), a, b);
                    });
                });
            } else {
                log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》", v.getClass().getSimpleName(), k, v);
            }
        });
    }

    /**
     * 将对象每一个key-value都打印出来
     *
     * @param o 打印List、Map类型数组
     */
    public void print(Object o) {
        if (o instanceof List) {
            ((List<Object>) o).forEach(l ->
                    print(l)
            );
        } else if (o instanceof Map) {
            ((Map<String, Object>) o).forEach((k, v) -> {
                if (v instanceof List) {
                    for (Object l : (List<Object>) v) {
                        if (!(l instanceof Map) && !(l instanceof List)) {
                            log.info("类型:《" + v.getClass().getSimpleName() + "》——KEY:《" + k + "》——VALUE:《" + v + "》");
                            break;
                        } else {
                            print(l);
                        }
                    }
                } else if (v instanceof Map) {
                    print(v);
                } else {
                    if (ObjectUtils.isNotEmpty(v))
                        log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》",v.getClass().getSimpleName(),k,v);
                    else
                        log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》",null,k,v);
                }
            });
        }
    }

    /**
     * 只打印Map第一层
     *
     * @param result 打印的Map类型数组
     */
    public void printMap(Map result) {
        result.forEach((k, v) -> {
            if (ObjectUtils.isNotEmpty(v))
                log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》",v.getClass().getSimpleName(),k,v);
            else
                log.info("类型:《{}》——KEY:《{}》——VALUE:《{}》",null,k,v);
        });
    }

    /**
     * Map转Json打印
     * @param result
     */
    public void printMapToJson(Map<String, Object> result) {
        result.forEach((k, v) -> {
            if (ObjectUtils.isNotEmpty(v))
                log.info("Json打印-类型:《{}》——KEY:《{}》——VALUE:{}",v.getClass().getSimpleName(),k,JSON.toJSONString(v));
            else
                log.info("Json打印-类型:《{}》——KEY:《{}》——VALUE:{}",null,k,JSON.toJSONString(v));
        });
    }
}
