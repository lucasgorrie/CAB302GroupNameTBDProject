package com.example.cab302groupnametbdproject.model.passwords;

import com.example.cab302groupnametbdproject.model.SqliteConnection;
import com.example.cab302groupnametbdproject.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    + "(1, 3, 'Pass1!', '00100110'),"
                    + "(3, 3, 'Pass2@', '10111000'),"
                    + "(2, 1, 'Pass3#', '11111000')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a new Password to the DB table passwords. Requires int arguments for FKs
    @Override
    public void addPassword(Password password) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO passwords (user_id, website_id, password, key) VALUES (?, ?, ?, ?)");
            statement.setInt(1, password.getUser_id());
            statement.setInt(2, password.getWebsite_id());
            statement.setString(3, password.getPasswordContent());
            statement.setString(4, password.getKey());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Updates an existing password in the passwords table with a Password object argument. Cannot change FKs
    @Override
    public void updatePassword(Password password) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE passwords SET password = ?, key = ? WHERE id = ?");
            statement.setString(1, password.getPasswordContent());
            statement.setString(2, password.getKey());
            statement.setInt(3, password.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletes a password from the database given a Password object's PK
    @Override
    public void deletePassword(Password password) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM passwords WHERE id = ?");
            statement.setInt(1, password.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns a Password object based on an id
    @Override
    public Password getPassword(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM passwords WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                int website_id = resultSet.getInt("website_id");
                String password_content = resultSet.getString("password");
                String key = resultSet.getString("key");
                Password password = new Password(user_id, website_id, password_content, key);
                password.setId(id);
                return password;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}