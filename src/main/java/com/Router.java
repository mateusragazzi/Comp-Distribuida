package com;

import com.adapter.database.ActorDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.controller.ActorController;
import com.adapter.rest.controller.MovieController;
import com.adapter.rest.controller.SearchController;
import com.domain.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
    private final ActorController actorController = new ActorController(new ActorDao());
    private final MovieController movieController = new MovieController();
    private final SearchController controller = new SearchController();
    private Pattern pattern;

    public Router() {
        this.pattern = Pattern.compile("/\\w/\\d");
    }

    public Response route(Request request) {
        final String path = request.getPath();

        if ("/actors".equals(path)) {
            if ("get".equalsIgnoreCase(request.getMethod())) {
                if (hasParams(request.getParams()))
                    return actorController.getById(request);
                return actorController.getAll(request);
            }

            if ("post".equalsIgnoreCase(request.getMethod()))
                return actorController.create(request);
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.METHOD_NOT_ALLOWED.getMessage());
        } else if (Pattern.matches("/actors/\\d", path)) {
            return actorController.getById(request);
        } else if ("/movies".equals(path)) {
            return null;

        } else if ("/movies/\\d".matches(path)) {
            return null;

        } else if ("/search".equals(path)) {
            return null;
        } else {
            return new Response(HttpStatus.NOT_FOUND.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.NOT_FOUND.getMessage());
        }
    }

    private boolean hasParams(String params) {
        return !params.isEmpty();
    }
}
