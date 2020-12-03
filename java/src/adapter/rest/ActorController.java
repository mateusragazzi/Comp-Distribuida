package src.adapter.rest;

import src.domain.response.HttpResponseDTO;
import src.domain.response.Response;

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
