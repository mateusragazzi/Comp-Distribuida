import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.adapter.database.MovieDao;
import src.domain.entity.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieDAOTest {
    private MovieDao movieDao = new MovieDao();
    private long IDForTest, IDToDelete;

    @BeforeEach
    void setUp() {
        Movie movie = new Movie("2012", "2012 is a 2009 American disaster film directed by Roland Emmerich. It was produced by Harald Kloser, Mark Gordon, and Larry J. Franco, and written by Kloser and Emmerich. The film stars John Cusack, Chiwetel Ejiofor, Amanda Peet, Oliver Platt, Thandie Newton, Danny Glover, and Woody Harrelson. The plot follows geologist Adrian Helmsley (Ejiofor), who discovers the Earth's crust is becoming unstable after a massive solar flare caused by an alignment of the planets, and novelist Jackson Curtis (Cusack) as he attempts to bring his family to safety as the world is destroyed by a series of extreme natural disasters caused by this. The film refers to Mayanism and the 2012 phenomenon in its portrayal of cataclysmic events.");
        Movie insertedMovie = movieDao.create(movie);
        IDForTest = insertedMovie.getID();
    }

    @AfterEach
    void tearDown() {
        movieDao.delete(IDToDelete);
    }

    @Test
    void mustPersistMovie() {
        Movie movie = new Movie("2012", "2012 is a 2009 American disaster film directed by Roland Emmerich. It was produced by Harald Kloser, Mark Gordon, and Larry J. Franco, and written by Kloser and Emmerich. The film stars John Cusack, Chiwetel Ejiofor, Amanda Peet, Oliver Platt, Thandie Newton, Danny Glover, and Woody Harrelson. The plot follows geologist Adrian Helmsley (Ejiofor), who discovers the Earth's crust is becoming unstable after a massive solar flare caused by an alignment of the planets, and novelist Jackson Curtis (Cusack) as he attempts to bring his family to safety as the world is destroyed by a series of extreme natural disasters caused by this. The film refers to Mayanism and the 2012 phenomenon in its portrayal of cataclysmic events.");
        Movie insertedMovie = movieDao.create(movie);
        Movie persistedMovie = movieDao.read(insertedMovie.getID());
        IDToDelete = insertedMovie.getID();

        assertEquals(movie, persistedMovie);
    }

    @Test
    void mustUpdateMovie() {
        Movie movie = new Movie("2012", "2012 is a 2009 American disaster film directed by Roland Emmerich. It was produced by Harald Kloser, Mark Gordon, and Larry J. Franco, and written by Kloser and Emmerich. The film stars John Cusack, Chiwetel Ejiofor, Amanda Peet, Oliver Platt, Thandie Newton, Danny Glover, and Woody Harrelson. The plot follows geologist Adrian Helmsley (Ejiofor), who discovers the Earth's crust is becoming unstable after a massive solar flare caused by an alignment of the planets, and novelist Jackson Curtis (Cusack) as he attempts to bring his family to safety as the world is destroyed by a series of extreme natural disasters caused by this. The film refers to Mayanism and the 2012 phenomenon in its portrayal of cataclysmic events.");
        movieDao.update(this.IDForTest, movie);
        Movie updatedMovie = movieDao.read(this.IDForTest);

        assertEquals(movie, updatedMovie);
    }

    @Test
    void mustReadOneMovie() {
        Movie movie = new Movie("2012", "2012 is a 2009 American disaster film directed by Roland Emmerich. It was produced by Harald Kloser, Mark Gordon, and Larry J. Franco, and written by Kloser and Emmerich. The film stars John Cusack, Chiwetel Ejiofor, Amanda Peet, Oliver Platt, Thandie Newton, Danny Glover, and Woody Harrelson. The plot follows geologist Adrian Helmsley (Ejiofor), who discovers the Earth's crust is becoming unstable after a massive solar flare caused by an alignment of the planets, and novelist Jackson Curtis (Cusack) as he attempts to bring his family to safety as the world is destroyed by a series of extreme natural disasters caused by this. The film refers to Mayanism and the 2012 phenomenon in its portrayal of cataclysmic events.");
        Movie readMovie = movieDao.read(this.IDForTest);

        assertEquals(movie, readMovie);
    }

    @Test
    void mustDeleteMovie() {
        boolean response = movieDao.delete(this.IDForTest);
        assertTrue(response);
    }
}