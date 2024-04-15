package com.example.cab302groupnametbdproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Establishes a connection with the SQLite DB
public class SqliteConnection {
    private static Connection instance = null;

    private SqliteConnection() {
        String url = "jdbc:sqlite:PasswordManager.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
