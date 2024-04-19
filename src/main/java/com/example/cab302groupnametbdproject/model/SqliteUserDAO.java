package com.example.cab302groupnametbdproject.model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

// Handles DAO and CRUD
public class SqliteUserDAO implements UserDAO {
    private Connection connection;

    // Constructor
    public SqliteUserDAO(){
        connection = SqliteConnection.getInstance();
        createTable();

        // Insert data for testing
        insertTestingData();
    }

    private void createTable() {
        // Create the users table, if it doesn't already exist
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            // Replace this with better logging later on
            e.printStackTrace();
        }
    }

    private void insertTestingData() {
        try {
            // Clear deployed DB before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);

            // Insert testing data
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, email, password) VALUES "
                    + "('Lucas', 'Gorrie', '123@example.com', 'PasswordPlainText1!'),"
                    + "('Alyx', 'Vance', 'avance@example.com', 'PasswordTest2@'),"
                    + "('Cave', 'Johnson', 'cave@aperturescience.com', 'PassPassPass3#')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
