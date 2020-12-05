package com.adapter.rest.controller;

import com.adapter.database.ActorDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.serializer.JsonSerializer;
import com.domain.entity.Actor;

import java.util.List;

public class ActorController extends Controller {
    private final ActorDao dao;

    public ActorController(ActorDao dao) {
        this.dao = dao;
    }

    public Response getAll(Request request) {
        String contentType = request.getContentType();
        List<Actor> actors = dao.read();
        return new Response(200, contentType, actors);
    }

    public Response create(Request request) {
        String contentType = request.getContentType();
        String actorData = request.getBody();
        Actor actor = (Actor) new JsonSerializer().desserialize(actorData, Actor.class);
        dao.create(actor);
        return new Response(200, contentType, actor);
    }
}
