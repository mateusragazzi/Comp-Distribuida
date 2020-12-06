package com.adapter.database;

import com.domain.entity.Actor;
import com.domain.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericDaoTest {
    ActorDao actorDao = new ActorDao();
    MovieDao movieDao = new MovieDao();
    GenericDao genericDao = new GenericDao();

    @Test
    void mustFindActorByName() {
        String name = "Pacino";
        Actor actor = actorDao.read((long) 4);
        List<Object> listOfItems = genericDao.read(name);
        Actor foundedActor = (Actor) listOfItems.get(0);

        assertEquals(actor, foundedActor);
    }

    @Test
    void mustFindActorByBirthdate() {
        String birthdate = "1940-04-25";
        Actor actor = actorDao.read((long) 4);
        List<Object> listOfItems = genericDao.read(birthdate);
        Actor foundedActor = (Actor) listOfItems.get(0);

        assertEquals(actor, foundedActor);
    }

    @Test
    void mustFindMovieByTitle() {
        String title = "The Godfather";
        Movie movie = movieDao.read((long) 2);
        List<Object> listOfItems = genericDao.read(title);
        Movie foundedMovie = (Movie) listOfItems.get(0);

        assertEquals(movie, foundedMovie);
    }

    @Test
    void mustFindMovieBySynopsis() {
        String synopsis = "Salvador (Puig Antich) (or Salvador) is a 2006 Spanish film directed by Manuel Huerga.";
        Movie movie = movieDao.read((long) 8);
        List<Object> listOfItems = genericDao.read(synopsis);
        Movie foundedMovie = (Movie) listOfItems.get(0);

        assertEquals(movie, foundedMovie);
    }
}