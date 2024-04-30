package com.example.cab302groupnametbdproject.model.associatedWebsites;

import com.example.cab302groupnametbdproject.model.SqliteConnection;
import com.example.cab302groupnametbdproject.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Handles DAO and CRUD
public class SqliteAssociatedWebsiteDAO implements AssociatedWebsiteDAO {

    private Connection connection;

    // Constructor
    public SqliteAssociatedWebsiteDAO(){
        connection = SqliteConnection.getInstance(); // Establish connection with DB
        createTable(); // Create table if it does not exist
    }

    private void createTable() {
        // Create the websites table, if it doesn't already exist
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
            String clearQuery = "DELETE FROM websites";
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

    // Adds a Website object into the websites table
    @Override
    public void addWebsite(Website website) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO websites (URL) VALUES (?)");
            statement.setString(1, website.getURL());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletes a row from the websites table based on the id of a Website object
    @Override
    public void deleteWebsite(Website website) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM websites WHERE id = ?");
            statement.setInt(1, website.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns a Website object from the websites table based on a PK argument
    @Override
    public Website getWebsite(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM websites WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String URL = resultSet.getString("URL");
                Website website = new Website(URL);
                website.setId(id);
                return website;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns a list containing Website objects of all the websites in the DB table "websites"
    // On its own, this will only return the objects themselves. Use something like this to get specific properties:
    // List<String> URLs = getAllWebsites().stream().map(Website::getURL).toList(); <-- gets all URLs
    @Override
    public List<Website> getAllWebsites() {
        List<Website> websites = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM websites";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String URL = resultSet.getString("URL");
                Website website = new Website(URL);
                website.setId(id);
                websites.add(website);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return websites;
    }

}
