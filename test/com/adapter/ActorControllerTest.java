package com.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.RequestBuilder;
import com.adapter.rest.ActorController;
import com.adapter.rest.Request;
import com.domain.response.Response;

public class ActorControllerTest {
    ActorController controller = new ActorController();

    @Test
    void shouldGetAllActors() {
        Request request = new RequestBuilder().withPath("actors").withMethod("get").build();

        Response response = controller.getAll(request);

        Assertions.assertNotNull(response);
    }

    @Test
    void shouldCreateAnActor() {
        String request = "POST /actors HTTP/1.1\r\nHost: http://localhost:8080\r\n\r\n{\"name\":\"billy\"}";
        String[] splitted = request.split("\\r\\n\\r\\n");

        Assertions.assertNotNull(splitted);
    }
}
