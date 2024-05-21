package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;

public class AboutViewController implements Initializable {

    @FXML
    public Button backbutton;
    @FXML
    public VBox mainContent;
    @FXML
    public Button userbutton;
    @FXML
    public Button homebutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    @FXML
    protected void BackButton() throws IOException {
        Stage stage = (Stage) backbutton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        if (LoginController.loggedInUser.getUserType().equals("PARENT")) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("child-interface-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}
