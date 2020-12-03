package src.adapter.database;

import src.domain.entity.Actor;

import java.sql.*;

public class ActorDao {
    Connection connection;

    public ActorDao() {
        ConnectionManager cm = new ConnectionManager();
        this.connection = cm.createConnection();
    }

    public Actor create(Actor actor) {
        String sql = String.format("INSERT INTO actors (name, birthdate) VALUES(?, ?);");
        try {
            PreparedStatement preparedStmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, actor.getName());
            preparedStmt.setString (2, actor.getBirthdate());
            int affectedRows = preparedStmt.executeUpdate();

            if(affectedRows == 0) {
                throw new SQLException("Error in creating movie.");
            }

            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    actor.setID(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            preparedStmt.close();
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return actor;
    }

    public Object read() {
        return null;
    }

    public Object read(Integer ID) {
        return null;
    }

    public Object update(Integer ID, Object object) {
        return null;
    }

    public Object delete(Integer ID) {
        return null;
    }
}
