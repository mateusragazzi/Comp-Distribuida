package com.adapter.rest;

import com.domain.response.HttpResponseDTO;
import com.domain.response.Response;

public class ActorController extends Controller {
    public Response getAll(Request request) {
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
