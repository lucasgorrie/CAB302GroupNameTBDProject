package com.example.cab302groupnametbdproject.model.passwords;

import com.example.cab302groupnametbdproject.model.SqliteConnection;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlitePasswordDAO implements PasswordDAO {

    private Connection connection;

    // Constructor
    public SqlitePasswordDAO(){
        connection = SqliteConnection.getInstance(); // Establish connection with DB
        createTable(); // Create table if it does not exist
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
            String insertQuery = "INSERT INTO passwords (user_id, website_id, password) VALUES "
                    + "(1, 3, 'Pass1!'),"
                    + "(3, 3, 'Pass2@'),"
                    + "(2, 1, 'Pass3#')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a new Password to the DB table passwords. Requires int arguments for FKs
    @Override
    public void addPassword(Password password) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO passwords (user_id, website_id, password) VALUES (?, ?, ?)");
            statement.setInt(1, password.getUser_id());
            statement.setInt(2, password.getWebsite_id());
            statement.setString(3, password.getPasswordContent());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Updates an existing password in the passwords table with a Password object argument. Cannot change FKs
    @Override
    public void updatePassword(Password password) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE passwords SET password = ? WHERE id = ?");
            statement.setString(1, password.getPasswordContent());
            statement.setInt(2, password.getId());
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
                Password password = new Password(user_id, website_id, password_content);
                password.setId(id);
                return password;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns a list containing Password objects of all the passwords in the DB table "passwords"
    // On its own, this will only return the objects themselves. Use something like this to get specific properties:
    // List<String> contents = getAllPasswords().stream().map(Password::getPasswordContent).toList(); <-- gets all contents
    @Override
    public List<Password> getAllPasswords() {
        List<Password> passwords = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM passwords";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int website_id = resultSet.getInt("website_id");
                String password_content = resultSet.getString("password");
                Password password = new Password(user_id, website_id, password_content);
                password.setId(id);
                passwords.add(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
