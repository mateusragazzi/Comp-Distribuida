package src.adapter.rest.serializer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.domain.entity.Actor;

public class JsonSerializerTest {

    @Test
    void shouldSerializeAnObjectToJsonString() {
        Actor request = new Actor("john", "12/12/2000");
        String expectedJson = "{\"name\":\"john\",\"birthdate\":\"12/12/2000\"}";

        String actualJson = new JsonSerializer().serialize(request);

        Assertions.assertEquals(expectedJson, actualJson);
    }
}
