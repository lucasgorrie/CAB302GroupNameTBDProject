package com.example.cab302groupnametbdproject.model.users;

import com.example.cab302groupnametbdproject.model.SqliteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handles DAO and CRUD
 */
public class SqliteUserDAO implements UserDAO {
    private Connection connection;

    // Constructor
    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance(); // Establish connection with DB
        createTable(); // Create table if it does not exist
    }

    // Create the users table, if it doesn't already exist
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_type VARCHAR NOT NULL,"
                    + "parent_id INTEGER,"
                    + "username VARCHAR NOT NULL,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "FOREIGN KEY (\"parent_id\") REFERENCES \"users\"(\"id\")"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            // Replace this with better logging later on
            e.printStackTrace();
        }
    }

    // Adds a new User object to the users table
    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (user_type, parent_id, username, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getUserType());
            statement.setInt(2, user.getParentId());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Updates a User's data in the users table based on a User object argument
    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET user_type = ?, parent_id = ?, username = ?, firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?");
            statement.setString(1, user.getUserType());
            statement.setInt(2, user.getParentId());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletes a User from the DB based on a User object argument
    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns a single User object based on an ID argument
    @Override
    public User getUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String user_type = resultSet.getString("user_type");
                Integer parent_id = resultSet.getInt("parent_id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(user_type, username, firstName, lastName, email, password);
                user.setId(id);
                if(parent_id != 0){
                    user.setParentId(parent_id);
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns a User object based on a username argument
    public User getUserFromUserName(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String user_type = resultSet.getString("user_type");
                Integer parent_id = resultSet.getInt("parent_id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(user_type, username, firstName, lastName, email, password);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns a list containing User objects of all the users in the DB table "users"
    // On its own, this will only return the objects themselves. Use something like this to get specific properties:
    // List<String> firstNames = getAllUsers().stream().map(User::getFirstName).toList(); <-- gets all first names
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String user_type = resultSet.getString("user_type");
                Integer parent_id = resultSet.getInt("parent_id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(user_type, username, firstName, lastName, email, password);
                user.setParentId(parent_id);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Check if username exists already
    @Override
    public Boolean doesUsernameExist(String username) {
        List<User> allUsers = getAllUsers();
        for (User user : allUsers) {
            if (Objects.equals(username, user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
