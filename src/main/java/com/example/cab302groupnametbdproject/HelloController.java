package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // Constructor
    private UserDAO userDAO;
    public HelloController(){
        userDAO = new SqliteUserDAO();
    }

}