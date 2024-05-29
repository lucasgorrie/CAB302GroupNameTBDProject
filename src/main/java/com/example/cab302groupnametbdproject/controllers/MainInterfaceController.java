package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


public class MainInterfaceController implements Initializable {
    public Button associateWebsitesButton;
    public Button childAccountsButton;
    public Button childCreateButton;
    @FXML
    private Button userbutton;
    @FXML
    private Button signOutButton;
    @FXML
    private Button aboutbutton;
    @FXML
    private Button addPasswordButton;

    // Init
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    // Navigate to user info page
    @FXML
    protected void onUserButtonClick() throws IOException {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    // Add password button method
    @FXML
    protected void onAddPasswordClick() throws IOException {
        Stage stage = (Stage) addPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    // Create child button method
    public void onChildCreateButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) childCreateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-child-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    // Back button
    public static class BackButton extends Button {
        public BackButton(BorderPane mainLayout, Pane mainContent) {
            super("Back");
            this.setOnAction(event -> mainLayout.setCenter(mainContent));
            this.setStyle("-fx-background-color: #FFC04C; -fx-text-fill: black;");
            BorderPane.setMargin(this, new Insets(10));
            BorderPane.setAlignment(this, Pos.TOP_LEFT);
        }
    }


    // Sign out button method
    @FXML
    private void onSignOutButtonClick() throws IOException {
        loggedInUser = null;
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    // Associated websites table method
    @FXML
    private void onAssociatedWebsitesClick() throws IOException {
        Stage stage = (Stage) associateWebsitesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    // About button method
    @FXML
    private void onAboutButtonClick() throws IOException {
        Stage stage = (Stage) aboutbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    // Child accounts button method
    @FXML
    private void onChildAccountsButtonClick() throws IOException {
        Stage stage = (Stage) childAccountsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("childaccount-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
