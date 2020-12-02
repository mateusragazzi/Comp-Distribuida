package src.adapter.database;

import java.sql.Connection;

public class ActorDao {
    Connection connection;

    public ActorDao(ConnectionFactory connectionFactory) {
        this.connection = connectionFactory.createConnection();
    }
}
