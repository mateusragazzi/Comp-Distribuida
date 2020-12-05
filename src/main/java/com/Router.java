package com;

import com.adapter.database.ActorDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.controller.ActorController;
import com.adapter.rest.controller.MovieController;
import com.adapter.rest.controller.SearchController;

public class Router {
    private final ActorController actorController = new ActorController(new ActorDao());
    private final MovieController movieController = new MovieController();
    private final SearchController controller = new SearchController();

    public Response route(Request request) {
        final String path = request.getPath();

        if ("actors".equals(path)) {
            if ("get".equals(request.getMethod()))
                return actorController.getAll(request);
            if ("post".equals(request.getMethod()))
                return actorController.create(request);
            return new Response(405, request.getContentType(), "Method not allowed");
        } else if ("actors/\\d".matches(path)) {
            return null;
        } else if ("movies".equals(path)) {
            return null;

        } else if ("movies/\\d".matches(path)) {
            return null;

        } else if ("search".equals(path)) {
            return null;
        } else {
            return new Response(404, request.getContentType(), "URL Not found");
        }
    }
}
