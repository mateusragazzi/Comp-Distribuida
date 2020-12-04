package src.adapter.database;

import src.domain.entity.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    ConnectionManager connectionManager;

    public ActorDao() {
        this.connectionManager = new ConnectionManager();
    }

    public Actor create(Actor actor) {
        Connection connection = this.connectionManager.createConnection();

        String sql = String.format("INSERT INTO actors (name, birthdate) VALUES(?, ?);");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, actor.getName());
            preparedStmt.setString(2, actor.getBirthdate());
            preparedStmt.execute();

            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    actor.setID(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return actor;
    }

    public List<Actor> read() {
        Connection connection = this.connectionManager.createConnection();

        List<Actor> actorsList = new ArrayList<>();
        String sql = "SELECT * FROM actors;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                long queriedID = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String birthDate = resultSet.getString("birthdate");
                actorsList.add(new Actor(queriedID, name, birthDate));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actorsList;
    }

    public Actor read(Long ID) {
        Connection connection = this.connectionManager.createConnection();

        Actor actor = null;

        String sql = "SELECT * FROM actors WHERE id = ?;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, ID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String birthDate = resultSet.getString("birthdate");
                actor = new Actor(ID, name, birthDate);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actor;
    }

    public boolean update(Long ID, Actor actor) {
        Connection connection = this.connectionManager.createConnection();

        boolean response = false;

        String sql = "UPDATE actors SET name = ?, birthdate = ? WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, actor.getName());
            stmt.setString(2, actor.getBirthdate());
            stmt.setLong(3, ID);

            stmt.executeUpdate();
            stmt.close();

            response = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public boolean delete(Long ID) {
        Connection connection = this.connectionManager.createConnection();

        boolean response = false;
        String sql = "DELETE FROM actors WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, ID);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                response = true;
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }
}
