package com.adapter.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDao {
    Connection connection;

    public GenericDao() {
        connection = new ConnectionManager().createConnection();
    }

    /**
     * Função que localiza um ator ou filme, dado um termo.
     *
     * @param term termo informado pelo usuário.
     * @return lista de objetos encontrados na busca.
     */
    public List<Object> read(String term) {
        List<Object> listOfObjects = null;

        String sql = "SELECT * FROM actors, movies " +
                     "WHERE name LIKE '%{term}%' OR birthdate LIKE '%{term}%' " +
                     "OR title LIKE '%{term}%' OR synopsis LIKE '%{term}%';";

        sql.replaceAll("\\{term}", term);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            String table = "";
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                table = rsmd.getTableName(i);

            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfObjects;
    }

}
