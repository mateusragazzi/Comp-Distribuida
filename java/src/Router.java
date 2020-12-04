package src;

import src.adapter.rest.*;
import src.adapter.rest.HttpResponseDTO;
import src.adapter.rest.Response;
import src.adapter.rest.controller.ActorController;
import src.adapter.rest.controller.MovieController;
import src.adapter.rest.controller.SearchController;

public class Router {
    private final ActorController actorController = new ActorController();
    private final MovieController movieController = new MovieController();
    private final SearchController controller = new SearchController();

    public Response route(Request request) {
        final String path = request.getPath();

        if ("actors".equals(path)) {
            return actorController.getAll(request);
        } else if ("actors/\\d".matches(path)) {
            return null;
        } else if ("movies".equals(path)) {
            return null;

        } else if ("movies/\\d".matches(path)) {
            return null;

        } else if ("search".equals(path)) {
            return null;
        } else {
            HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
            httpResponseDTO.contentType = "text/json";
            httpResponseDTO.statusCode = 404;
            httpResponseDTO.body = "{message: URL not found}";
            return new Response(httpResponseDTO);
        }
    }
}
