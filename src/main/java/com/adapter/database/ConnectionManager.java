package com.adapter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements ConnectionFactory {
    private Connection connection = null;

    @Override
    public Connection createConnection() {
        if (this.connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Movies", "root", "root");
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return this.connection;
    }
}
