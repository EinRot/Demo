package com.json.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DemoDeserializer extends JsonDeserializer<Long> {

	@Override
	public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
			String value = jsonParser.getValueAsString();
		return Long.valueOf(value+"1");
	}
}