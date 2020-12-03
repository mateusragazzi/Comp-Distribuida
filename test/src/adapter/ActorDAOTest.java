package src.adapter;

import org.junit.jupiter.api.Test;
import src.adapter.database.ActorDao;
import src.domain.entity.Actor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorDAOTest {

    private final ActorDao actorDao = new ActorDao();

    @Test
    void create() {
        Actor actor = new Actor("Mateus", "1999-01-05");
        long ID = actorDao.create(actor).getID();

        assertEquals(actor.getID(), ID);
    }
}