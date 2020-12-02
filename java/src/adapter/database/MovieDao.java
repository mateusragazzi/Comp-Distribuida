package src.adapter.database;

import java.sql.Connection;

public class MovieDao {
    Connection connection;

    public MovieDao(ConnectionFactory connectionFactory) {
        this.connection = connectionFactory.createConnection();
    }
}
