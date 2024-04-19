package com.example.cab302groupnametbdproject.model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

// Handles DAO and CRUD
public class SqliteAssociatedWebsiteDAO implements AssociatedWebsiteDAO {

    private Connection connection;

    // Constructor
    public SqliteAssociatedWebsiteDAO(){
        connection = SqliteConnection.getInstance();
        createTable();

        // Insert data for testing
        insertTestingData();
    }

    private void createTable() {
        // Create the users table, if it doesn't already exist
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS websites ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "URL VARCHAR NOT NULL"
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
            String insertQuery = "INSERT INTO websites (URL) VALUES "
                    + "('gmail.com'),"
                    + "('qut.edu.au.hiq.com'),"
                    + "('my.gov')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addWebsite(Website website) {

    }

    @Override
    public void deleteUser(Website website) {

    }

    @Override
    public Website getWebsite(int id) {
        return null;
    }

    @Override
    public List<Website> getAllWebsites() {
        return null;
    }
}
