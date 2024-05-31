package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.PasswordMangerMain;
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


/**
 * Controller for main menu
 */
public class MainMenuController implements Initializable {
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

    /**
     * Button to redirect to user page
     */
    @FXML
    protected void onUserButtonClick() throws IOException {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("user-info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public static class BackButton extends Button {
        public BackButton(BorderPane mainLayout, Pane mainContent) {
            super("Back");
            this.setOnAction(event -> mainLayout.setCenter(mainContent));
            this.setStyle("-fx-background-color: #FFC04C; -fx-text-fill: black;");
            BorderPane.setMargin(this, new Insets(10));
            BorderPane.setAlignment(this, Pos.TOP_LEFT);
        }
    }
    /**
     * Button to sign out user
     */
    @FXML
    private void onSignOutButtonClick() throws IOException {
        loggedInUser = null;
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button to redirect to add password page
     */
    @FXML
    protected void onAddPasswordClick() throws IOException {
        Stage stage = (Stage) addPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("add-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button to redirect to create child account page
     */
    public void onChildCreateButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) childCreateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("create-child-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button to redirect to associated websites page
     */
    @FXML
    private void onAssociatedWebsitesClick() throws IOException {
        Stage stage = (Stage) associateWebsitesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("associated-websites-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    /**
     * Button to redirect to about page
     */
    @FXML
    private void onAboutButtonClick() throws IOException {
        Stage stage = (Stage) aboutbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    /**
     * Button to redirect to associated child accounts page
     */
    @FXML
    private void onChildAccountsButtonClick() throws IOException {
        Stage stage = (Stage) childAccountsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("child-accounts-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
