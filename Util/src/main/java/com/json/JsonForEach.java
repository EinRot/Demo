package com.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/6/7
 *
 */
public class JsonForEach {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"mid\":188910318,\"did\":\"M2202205090030\",\"cmd\":\"CMD-213\",\"sta\":1654228747,\"sl\":[{\"sid\":861677100,\"s\":1,\"v\":229230,\"c\":53,\"po\":0,\"t\":32,\"me\":7906,\"l\":0,\"reme\":0}]}");
        json.get("sl").forEach(j->{
            try {
                Map<String, String> tags = new HashMap<>();
                Map<String, Object> valueList = new HashMap<>();
                tags.put("devsn", j.get("sid").asText());
                //——————————————————————————————————————————————————
                Iterator<Map.Entry<String, JsonNode>> fields = j.fields();
                while (fields.hasNext()){
                    Map.Entry<String, JsonNode> str = fields.next();
                    valueList.put(str.getKey(),str.getValue());
                }
                //——————————————————————————————————————————————————
                System.out.println(valueList);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
