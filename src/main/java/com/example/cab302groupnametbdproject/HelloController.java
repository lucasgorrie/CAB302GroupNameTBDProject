package com.example.cab302groupnametbdproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button continueButton;
    @FXML
    private Button loginBackButton;

    @FXML
    protected void onContinueButtonClick() throws IOException {
            Stage stage = (Stage) continueButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setScene(scene);
    }

    public void onLoginBackButtonClick() throws IOException {
        Stage stage = (Stage) loginBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }
}