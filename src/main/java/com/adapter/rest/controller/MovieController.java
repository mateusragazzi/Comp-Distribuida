package com.adapter.rest.controller;

import com.adapter.database.MovieDao;
import com.adapter.rest.Request;
import com.adapter.rest.Response;
import com.adapter.rest.serializer.JsonSerializer;
import com.domain.entity.Actor;
import com.domain.entity.Movie;

import java.util.List;

public class MovieController extends Controller {
    private final MovieDao dao;

    public MovieController(MovieDao dao) {
        this.dao = dao;
    }

    public Response getAll(Request request) {
        String contentType = request.getContentType();
        List<Movie> Movies = dao.read();
        return new Response(200, contentType, Movies);
    }

    public Response getById(Request request) {
        String contentType = request.getContentType();
        long id = Long.parseLong(request.getParams().get(0));
        Movie Movie = dao.read(id);
        return new Response(200, contentType, Movie);
    }

    public Response create(Request request) {
        String contentType = request.getContentType();
        String MovieData = request.getBody();
        Movie Movie = (Movie) new JsonSerializer().desserialize(MovieData, Movie.class);
        dao.create(Movie);
        return new Response(200, contentType, String.format("Movie %s was created", Movie.getTitle()));
    }

    public Response update(Request request) {
        String MovieData = request.getBody();
        Movie Movie = (Movie) new JsonSerializer().desserialize(MovieData, Movie.class);
        dao.update(Long.parseLong(request.getParams().get(0)), Movie);
        return new Response(200, request.getContentType(), String.format("Movie %s was updated", Movie.getTitle()));
    }

    public Response destroy(Request request) {
        dao.delete(Long.parseLong(request.getParams().get(0)));
        return new Response(200, request.getContentType(), "Movie removed.");
    }

    public Response getActors(Request request) {
        List<Actor> actors = dao.readActorsFromMovie(Long.parseLong(request.getParams().get(0)));
        return new Response(200, request.getContentType(), actors);
    }
}
