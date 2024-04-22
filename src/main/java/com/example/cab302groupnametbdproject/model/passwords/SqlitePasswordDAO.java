package com.example.cab302groupnametbdproject.model.passwords;

import com.example.cab302groupnametbdproject.model.SqliteConnection;

import java.sql.Connection;
import java.sql.Statement;

public class SqlitePasswordDAO implements PasswordDAO {

    private Connection connection;

    // Constructor
    public SqlitePasswordDAO(){
        connection = SqliteConnection.getInstance(); // Establish connection with DB
        createTable(); // Create table if it does not exist

        // Insert data for testing
        insertTestingData();
    }

    private void createTable() {
        // Create the passwords table, if it doesn't already exist
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS passwords("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_id INTEGER NOT NULL,"
                    + "website_id INTEGER NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "key VARCHAR NOT NULL,"
                    + "FOREIGN KEY(\"user_id\") REFERENCES \"users\"(\"id\"),"
                    + "FOREIGN KEY(\"website_id\") REFERENCES \"websites\"(\"id\")"
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
            String clearQuery = "DELETE FROM passwords";
            clearStatement.execute(clearQuery);

            // Insert testing data
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO passwords (user_id, website_id, password, key) VALUES "
                    + "(0, 0, 'Pass1!', '00100110'),"
                    + "(1, 0, 'Pass2@', '10111000'),"
                    + "(2, 1, 'Pass3#', '11111000')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Needs finishing, not started
    @Override
    public void addPassword(Password password) {

    }

    // Needs finishing, not started
    @Override
    public void updatePassword(Password password) {

    }

    // Needs finishing, not started
    @Override
    public void deletePassword(Password password) {

    }

    // Needs finishing, not started
    @Override
    public Password getPassword(int id) {
        return null;
    }
}
