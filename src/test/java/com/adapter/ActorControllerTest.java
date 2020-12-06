package com.adapter;

import com.adapter.database.ActorDao;
import com.adapter.rest.Response;
import com.adapter.rest.controller.ActorController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.RequestBuilder;
import com.adapter.rest.Request;
import org.mockito.Mockito;

public class ActorControllerTest {
    ActorController controller = new ActorController(Mockito.mock(ActorDao.class));

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
