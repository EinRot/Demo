package com.json.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO
 * 　　* @date 2022/7/26
 */
public class DemoSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(++aLong);
    }
}