package com.bigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO BigDecimal精度丢失测试
 * 　　* @date 2022/6/16
 */
public class BigDecimalDemo {
    public static void main(String[] args) throws JsonProcessingException {
        int a = 227610;
        Double d = Double.valueOf(a);
        d = d / 1000;
        System.out.println(d);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"mid\":188910318,\"did\":\"M2202205090030\",\"cmd\":\"CMD-213\",\"sta\":1654228747,\"sl\":[{\"sid\":861677100,\"s\":1,\"v\":229230,\"c\":53,\"po\":0,\"t\":32,\"me\":7906,\"l\":0,\"reme\":0}]}");
        json.get("sl").forEach(j -> {
            BigDecimal bigDecimal1 = new BigDecimal(j.get("v").intValue());
            BigDecimal bigDecimal2 = new BigDecimal(1000);
            BigDecimal r = bigDecimal1.divide(bigDecimal2, 2, RoundingMode.HALF_UP);
            System.out.println(r);
            System.out.println(new BigDecimal(j.get("v").intValue()).divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP).toString());
        });
    }
}
