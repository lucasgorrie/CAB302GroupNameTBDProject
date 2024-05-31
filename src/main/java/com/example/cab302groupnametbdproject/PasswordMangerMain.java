package com.example.cab302groupnametbdproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


/**
 * main class to start application 
 */
public class PasswordMangerMain extends Application {

    // Entry point
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("file:src/main/resources/com/example/cab302groupnametbdproject/images/logo.png"));
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.show();

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style2.css")).toExternalForm());
    }
    public static void main(String[] args) {
        launch();
    }
}
