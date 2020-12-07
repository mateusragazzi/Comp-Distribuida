package com;

import com.adapter.database.ActorDao;
import com.adapter.database.GenericDao;
import com.adapter.database.MovieDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.controller.ActorController;
import com.adapter.rest.controller.MovieController;
import com.adapter.rest.controller.SearchController;
import com.domain.HttpStatus;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
    private final ActorController actorController = new ActorController(new ActorDao());
    private final MovieController movieController = new MovieController(new MovieDao());
    private final SearchController searchController = new SearchController(new GenericDao());

    /**
     * Recebe uma Request e mapeia os endpoints aos controllers
     **/
    public Response route(Request request) {
        final String path = request.getPath();
        final String method = request.getMethod();
        final List<String> params = request.getParams();

        if ("actors".equals(path)) {
            if ("get".equalsIgnoreCase(method)) {
                if (hasParams(params))
                    return actorController.getById(request);
                return actorController.getAll(request);
            }
            if ("post".equalsIgnoreCase(method))
                return actorController.create(request);
            if ("put".equalsIgnoreCase(method))
                return actorController.update(request);
            if ("delete".equalsIgnoreCase(method))
                return actorController.destroy(request);

            return new Response(HttpStatus.METHOD_NOT_ALLOWED.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.METHOD_NOT_ALLOWED.getMessage());
        } else if ("movies".equals(path)) {
            if ("get".equalsIgnoreCase(method)) {
                if (hasParams(params)) {
                    if (params.size() > 1)
                        return movieController.getActors(request);
                    return movieController.getById(request);
                }
                return movieController.getAll(request);
            }
            if ("post".equalsIgnoreCase(method))
                return movieController.create(request);
            if ("put".equalsIgnoreCase(method))
                return movieController.update(request);
            if ("delete".equalsIgnoreCase(method))
                return movieController.destroy(request);

            return new Response(HttpStatus.METHOD_NOT_ALLOWED.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.METHOD_NOT_ALLOWED.getMessage());

        } else if ("search".equals(path)) {
            if ("get".equalsIgnoreCase(method)) {
                if (hasParams(params))
                    return searchController.search(request);
                return new Response(HttpStatus.METHOD_NOT_ALLOWED.getStatusCode(),
                        request.getContentType(),
                        HttpStatus.METHOD_NOT_ALLOWED.getMessage());
            }
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.METHOD_NOT_ALLOWED.getMessage());
        } else {
            return new Response(HttpStatus.NOT_FOUND.getStatusCode(),
                    request.getContentType(),
                    HttpStatus.NOT_FOUND.getMessage());
        }
    }

    private boolean hasParams(List<String> params) {
        return !params.isEmpty();
    }
}
