import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorDAOTest {
    private final ActorDao actorDao = new ActorDao();
    private long IDForTest, IDToDelete;

    @BeforeEach
    void setUp() {
        Actor actor = new Actor("Mateus Ragazzi", "1999-01-05");
        Actor insertedActor = actorDao.create(actor);
        IDForTest = insertedActor.getID();
    }

    @AfterEach
    void tearDown() {
        actorDao.delete(IDToDelete);
    }

    @Test
    void mustPersistActor() {
        Actor actor = new Actor("Mateus", "1999-01-05");
        Actor insertedActor = actorDao.create(actor);
        Actor persistedActor = actorDao.read(insertedActor.getID());
        IDToDelete = insertedActor.getID();

        assertEquals(actor, persistedActor);
    }

    @Test
    void mustUpdateActor() {
        Actor actor = new Actor("Mateus Ragazzi", "1999-01-05");
        actorDao.update(this.IDForTest, actor);
        Actor updatedActor = actorDao.read(this.IDForTest);

        assertEquals(actor, updatedActor);
    }

    @Test
    void mustReadOneActor() {
        Actor actor = new Actor("Mateus Ragazzi", "1999-01-05");
        Actor readActor = actorDao.read(this.IDForTest);

        assertEquals(actor, readActor);
    }

    @Test
    void mustDeleteActor() {
        boolean response = actorDao.delete(this.IDForTest);
        assertTrue(response);
    }
}