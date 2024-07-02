package com.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/5/24 15:23
 **/
public class Aaaa {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("D:/data/垃圾场/222.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { //读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();
        String data = buffer.toString();
        ObjectMapper mapper = new ObjectMapper();
        HashSet<HashMap> objects = new HashSet<>();
        JsonNode json = mapper.readTree(data);
        json.forEach(jsonNode -> {
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put(jsonNode.get("identifier").asText(),jsonNode.get("value").asText());
            objects.add(objectObjectHashMap);
        });
        objects.forEach(map->{
            map.forEach((key,value)->{
                System.out.println(key);
            });
        }
        );
    }
}
