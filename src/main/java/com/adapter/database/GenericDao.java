package com.adapter.database;

import com.domain.entity.Actor;
import com.domain.entity.Movie;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericDao {
    Connection connection;

    public GenericDao() {
        connection = new ConnectionManager().createConnection();
    }

    /**
     * Função que localiza um ator ou filme, dado um termo.
     *
     * @param term termo informado pelo usuário.
     * @return lista de objetos encontrados na busca.
     */
    public List<Object> read(String term) {
        List<Object> listOfObjects = null;
        ActorDao actorDao = new ActorDao();
        MovieDao movieDao = new MovieDao();
        List<Actor> actorList = actorDao.read(term);
        List<Movie> movieList = movieDao.read(term);

        listOfObjects = Stream.concat(actorList.stream(), movieList.stream()).collect(Collectors.toList());

        return listOfObjects;
    }

}
