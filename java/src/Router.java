package src;

import src.adapter.rest.Controller;
import src.adapter.rest.Request;
import src.domain.response.Response;


public class Router {

    public String route(Request request) {
        switch (request.getPath()) {
            case "actor":
                return "actor";
            case "movie":
                return "movie";
            case "search":
                return "search";
            default:
                return null;
        }
    }
}
