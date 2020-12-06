package com.adapter.rest.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.RequestBuilder;
import com.adapter.database.ActorDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.serializer.JsonSerializer;
import com.adapter.rest.serializer.Serializer;
import com.adapter.rest.serializer.XmlSerializer;
import com.domain.entity.Actor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class ActorControllerTest {
    ActorDao actorDao = Mockito.mock(ActorDao.class);
    ActorController controller = new ActorController(actorDao);
    Serializer jsonSerializer = new JsonSerializer();
    Serializer xmlSerializer = new XmlSerializer();

    @Test
    void shouldGetActorsInJson() {
        Request request = new RequestBuilder().withPath("actors").withMethod("get").build();
        Actor actor = new Actor("Billy, the tester", LocalDate.now().toString());
        List<Actor> actors = Collections.singletonList(actor);
        when(actorDao.read()).thenReturn(actors);
        String expectedJson = jsonSerializer.serialize(actors);

        Response response = controller.getAll(request);

        Assertions.assertEquals("text/json", response.getContentType());
        Assertions.assertEquals(expectedJson, response.getBody());
    }

    @Test
    void shouldGetActorsInXml() {
        Request request = new RequestBuilder().withContentType("text/xml").withPath("actors").withMethod("get").build();
        Actor actor = new Actor("Billy, the tester", LocalDate.now().toString());
        List<Actor> actors = Collections.singletonList(actor);
        when(actorDao.read()).thenReturn(actors);
        String expectedXml = xmlSerializer.serialize(actors);

        Response response = controller.getAll(request);

        Assertions.assertEquals("text/xml", response.getContentType());
        Assertions.assertEquals(expectedXml, response.getBody());
    }

}
