package com.brenda.dao;

import com.brenda.model.ToyModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToysDao {
    private static final String INSERT_TOYS_SQL = "INSERT INTO toys_table" + "  (name, price, quantity) VALUES " +
            " (?, ?, ?);";
    private static final String SELECT_TOY_BY_ID = "select name,price,quantity from toys_table where name =?";
    private static final String SELECT_ALL_TOYS = "select * from toys_table";
    private static final String DELETE_TOYS_SQL = "delete from toys_table where id = ?;";
    private static final String UPDATE_TOYS_SQL = "update toys_table set name = ?,price= ?, quantity =? where name = ?;";
    private String username = "root";
    private String password = "new_password";
    private String query
            = "INSERT into toys_table (name, price, quantity) values(?,?,?)";

    public ToysDao() {
    }

    protected Connection connection() {
        Connection conn = null;
        // Load driver class
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            Class.forName(driverClassName);
            // Obtain a connection
            String url = "jdbc:mysql://localhost/toys";
            conn = DriverManager.getConnection(
                    url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            if (ex instanceof SQLException) {
                printSQLException((SQLException) ex);
            } else ((ClassNotFoundException) ex).printStackTrace();
        }
        return conn;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void insertToysToTable(ToyModel model) throws SQLException {
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOYS_SQL)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getPrice());
            preparedStatement.setInt(3, model.getQuantity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean updateToys(ToyModel model) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TOYS_SQL);) {
            statement.setString(1, model.getName());
            statement.setInt(2, model.getPrice());
            statement.setInt(3, model.getQuantity());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteEntry(String name) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(DELETE_TOYS_SQL);) {
            statement.setString(1, name);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<ToyModel> getAllToys() {
        List<ToyModel> model = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = connection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TOYS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                model.add(new ToyModel(name, price, quantity));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return model;
    }

    public ToyModel selectToy(String t_name) {
        ToyModel model = null;
        // Step 1: Establishing a Connection
        try (Connection connection = connection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOY_BY_ID);) {
            preparedStatement.setString(1, t_name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                model = new ToyModel(name, price, quantity);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return model;
    }
}
