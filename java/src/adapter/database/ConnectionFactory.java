package src.adapter.database;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection();
}
