package com.zz.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/23
 * Time: 17:47
 * Description: No Description
 */
public class JsonObjectMapper extends ObjectMapper {
    private static final long seriaVersionUID = 1L;

    public JsonObjectMapper(){
        super();
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        });
    }
}
