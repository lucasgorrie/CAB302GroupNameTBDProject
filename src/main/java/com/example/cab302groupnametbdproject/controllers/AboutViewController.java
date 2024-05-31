package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.PasswordMangerMain;
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


/**
 * Controller for about page
 */
public class AboutViewController implements Initializable {

    @FXML
    public Button backbutton;
    @FXML
    public VBox mainContent;
    @FXML
    public Button userbutton;
    @FXML
    public Button homebutton;

    // Init
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    /**
     * Redirects to user info page
     */
    @FXML
    protected void onUserButtonClick() throws IOException {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("user-info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Redirects to home page
     */
    @FXML
    protected void BackButton() throws IOException {
        Stage stage = (Stage) backbutton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        if (LoginController.loggedInUser.getUserType().equals("PARENT")) {
            fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("main-menu-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("child-mainmenu-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}
