package com.adapter.database;

import com.domain.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    ConnectionManager connectionManager;

    public MovieDao() {
        this.connectionManager = new ConnectionManager();
    }

    /**
     * Função que cria o filme enviado por parâmetro
     * @param movie filme a ser criado
     * @return retorna o filme, com o ID gerado pelo banco.
     */
    public Movie create(Movie movie) {
        Connection connection = this.connectionManager.createConnection();

        String sql = String.format("INSERT INTO movies (title, synopsis) VALUES(?, ?);");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, movie.getTitle());
            preparedStmt.setString(2, movie.getSynopsis());
            preparedStmt.execute();

            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setID(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movie;
    }

    /**
     * Função que lê os filmees do banco, sem filtro.
     * @return lista de filmees lidos.
     */
    public List<Movie> read() {
        Connection connection = this.connectionManager.createConnection();

        List<Movie> moviesList = new ArrayList<>();
        String sql = "SELECT * FROM movies;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                long queriedID = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String synopsis = resultSet.getString("synopsis");
                moviesList.add(new Movie(queriedID, title, synopsis));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    /**
     * Função que um filme do banco.
     * @param ID identificador do filme a ser atualizado.
     * @return filme lido.
     */
    public Movie read(Long ID) {
        Connection connection = this.connectionManager.createConnection();

        Movie movie = null;

        String sql = "SELECT * FROM movies WHERE id = ?;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, ID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String synopsis = resultSet.getString("synopsis");
                movie = new Movie(ID, title, synopsis);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }

    /**
     * Função que atualiza um filme do banco, dado um filme como modelo.
     *
     * @param ID identificador do filme a ser atualizado.
     * @param movie dados a serem atualizados
     * @return response se foi atualizado (true) ou não (false)
     */
    public boolean update(Long ID, Movie movie) {
        Connection connection = this.connectionManager.createConnection();

        boolean response = false;

        String sql = "UPDATE movies SET title = ?, synopsis = ? WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getSynopsis());
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
     * Função que apaga um filme do banco.
     * @param ID identificador do filme a ser apagado.
     * @return response se foi apagado (true) ou não (false)
     */
    public boolean delete(Long ID) {
        Connection connection = this.connectionManager.createConnection();

        boolean response = false;
        String sql = "DELETE FROM movies WHERE id = ?;";
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
