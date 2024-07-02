package com.json.fastjson.analysis;

import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 将JSON转出结果通过配置生成单层Map对象
 * 　　* @date 2022/7/8
 *
 */
public class JsonServer {

    public static void main(String[] args) throws IOException {
        String url;
        url="";
        Reader reader = new FileReader(url);
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();

        //读取的json文件
        String data = buffer.toString();
        JsonAnalysis jsonAnalysis = new JsonAnalysis();
        Map<String, Object> dataMap = jsonAnalysis.analysis(data);
        jsonAnalysis.printMap(dataMap);
        JsonServer jsonServer = new JsonServer();
        Map<String, Object> result = jsonServer.format(dataMap);
//        jsonAnalysis.printMap(result);
    }

    public Map format(Map<String, Object> dataMap){
        Map<String, Object> result = new HashMap<>();
        dataMap.forEach((k,v)->{
            String[] keys= k.split(">>");
            if(keys.length>2){
                StringBuilder key = new StringBuilder(keys[keys.length-2]);
                key.append("_");
                key.append(keys[keys.length-1]);
                result.put(key.toString(),v);
            }else{
                result.put(k.replace(">>","_"),v);
            }
        });
        return result;
    }

    @Test
    public void test() throws IOException {
        String url;
        url="";
        Reader reader = new FileReader(url);
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        //读取的json文件
        String data = buffer.toString();
        JsonAnalysis j = new JsonAnalysis();
        Map<String, Object> dataMap = j.analysis(data);
        j.printMapToJson(dataMap);

        ConfigDto configDto = new ConfigDto();
        configDto.setMainList("data>>nodes");
        configDto.setKey("");
    }
}
