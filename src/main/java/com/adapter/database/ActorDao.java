package com.adapter.database;

import com.domain.entity.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    Connection connection;

    public ActorDao() {
        connection = new ConnectionManager().createConnection();
    }

    /**
     * Função que cria o ator enviado por parâmetro
     *
     * @param actor ator a ser criado
     * @return retorna o ator, com o ID gerado pelo banco.
     */
    public Actor create(Actor actor) {
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

    /**
     * Função que lê os atores do banco, sem filtro.
     *
     * @return lista de atores lidos.
     */
    public List<Actor> read() {
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

    /**
     * Função que um ator do banco.
     *
     * @param ID identificador do ator a ser atualizado.
     * @return ator lido.
     */
    public Actor read(Long ID) {
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

    /**
     * Tenta localizar um atores dado um termo qualquer.
     *
     * @param term indica o termo que o usuário buscou.
     * @return retorna 1 ou mais atores localizados.
     */
    public List<Actor> read(String term) {
        List<Actor> actorsList = new ArrayList<>();

        String sql = "SELECT * FROM actors WHERE name LIKE ? OR birthdate LIKE ?;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + term + "%");
            stmt.setString(2, "%" + term + "%");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                long ID = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String birthDate = resultSet.getString("birthdate");
                actorsList.add(new Actor(ID, name, birthDate));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actorsList;
    }

    /**
     * Função que atualiza um ator do banco, dado um ator como modelo.
     *
     * @param ID    identificador do ator a ser atualizado.
     * @param actor dados a serem atualizados
     * @return response se foi atualizado (true) ou não (false)
     */
    public boolean update(Long ID, Actor actor) {
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

    /**
     * Função que apaga um ator do banco.
     *
     * @param ID identificador do ator a ser apagado.
     * @return response se foi apagado (true) ou não (false)
     */
    public boolean delete(Long ID) {
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
