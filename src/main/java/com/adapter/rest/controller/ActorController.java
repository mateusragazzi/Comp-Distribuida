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

    public Response getById(Request request) {
        String contentType = request.getContentType();
        long id = Long.parseLong(request.getParams().get(0));
        Actor actor = dao.read(id);
        return new Response(200, contentType, actor);
    }

    public Response create(Request request) {
        String contentType = request.getContentType();
        String actorData = request.getBody();
        Actor actor = (Actor) new JsonSerializer().desserialize(actorData, Actor.class);
        dao.create(actor);
        return new Response(200, contentType, String.format("actor %s was created", actor.getName()));
    }

    public Response update(Request request) {
        String actorData = request.getBody();
        Actor actor = (Actor) new JsonSerializer().desserialize(actorData, Actor.class);
        dao.update(Long.parseLong(request.getParams().get(0)), actor);
        return new Response(200, request.getContentType(), String.format("actor %s was updated", actor.getName()));
    }

    public Response destroy(Request request) {
        dao.delete(Long.parseLong(request.getParams().get(0)));
        return new Response(200, request.getContentType(), "actor removed.");
    }
}
