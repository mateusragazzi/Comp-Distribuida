package src.adapter.rest.controller;

import src.adapter.database.ActorDao;
import src.adapter.rest.HttpResponseDTO;
import src.adapter.rest.Request;
import src.adapter.rest.Response;
import src.domain.entity.Actor;

import java.util.List;

public class ActorController extends Controller {
    private final ActorDao dao;

    public ActorController() {
        this.dao = new ActorDao();
    }

    public Response getAll(Request request) {
        List<Actor> actors = dao.read();
        HttpResponseDTO responseDto = createDto();
        return new Response(responseDto);
    }

    private HttpResponseDTO createDto() {
        final HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
        httpResponseDTO.body = "{actors: []}";
        httpResponseDTO.statusCode = 200;
        httpResponseDTO.contentType = "text/json";
        return httpResponseDTO;
    }
}
