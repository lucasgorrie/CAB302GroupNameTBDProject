package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button createUserButton;

    @FXML
    private Label loginInfo;



    //button to take user to signup view when hitting create user button
    @FXML
    protected void onCreateUserButtonClick() throws IOException {
        Stage stage = (Stage) createUserButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }



}
