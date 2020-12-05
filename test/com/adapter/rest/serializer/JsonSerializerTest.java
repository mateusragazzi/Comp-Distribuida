package com.adapter.rest.serializer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.domain.entity.Actor;
import com.domain.entity.Movie;

public class JsonSerializerTest {

    @Test
    void shouldSerializeAnActorToJsonString() {
        String name = "john";
        String birthdate = "12/12/2000";
        Actor request = new Actor(name, birthdate);
        String expectedJson = String.format("{\"name\":\"%s\",\"birthdate\":\"%s\"}", name, birthdate);

        String actualJson = new JsonSerializer().serialize(request);

        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void shouldSerializeAMovieToJsonString() {
        String title = "The Lord of The Rings";
        String synopsis = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.";
        Movie request = new Movie(title, synopsis);
        String expectedJson = String.format("{\"title\":\"%s\",\"synopsis\":\"%s\"}", title, synopsis);

        String actualJson = new JsonSerializer().serialize(request);

        Assertions.assertEquals(expectedJson, actualJson);
    }
}
