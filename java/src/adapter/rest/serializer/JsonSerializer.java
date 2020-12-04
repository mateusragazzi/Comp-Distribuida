package src.adapter.rest.serializer;

import com.google.gson.Gson;

public class JsonSerializer implements Serializer {

    @Override
    public String serialize(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
