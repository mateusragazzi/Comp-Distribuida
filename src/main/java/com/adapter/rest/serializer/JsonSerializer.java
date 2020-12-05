package com.adapter.rest.serializer;

import com.domain.entity.Actor;
import com.google.gson.Gson;

public class JsonSerializer implements Serializer {

    final Gson gson;

    public JsonSerializer() {
        this.gson = new Gson();
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }

    public Object desserialize(String json, Class target) {
        return gson.fromJson(json, target);
    }
}
