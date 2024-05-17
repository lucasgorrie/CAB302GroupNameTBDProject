package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInterfaceController {

    public Button associateWebsitesButton;
    public Button childAccountsButton;
    public Button childCreateButton;
    @FXML
    private BorderPane mainLayout;
    @FXML
    private VBox mainContent;

    @FXML
    private Button backbutton;

    @FXML
    private Button homebutton;

    @FXML
    private Button userbutton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button aboutbutton;
    @FXML
    private Button addPasswordButton;
    

    private void switchToPage(String pageName) {
        mainLayout.setCenter(createContentPage(pageName));
    }

    private BorderPane createContentPage(String pageName) {
        Label titleLabel = new Label(pageName);
        titleLabel.setAlignment(Pos.CENTER);

        VBox content = new VBox(titleLabel);
        content.setAlignment(Pos.CENTER);

        BackButton backButton = new BackButton(mainLayout, mainContent);

        BorderPane pageLayout = new BorderPane();
        pageLayout.setTop(backButton);
        pageLayout.setCenter(content);

        return pageLayout;
    }

    @FXML
    protected void onAddPasswordClick() throws IOException {
        Stage stage = (Stage) addPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void onChildCreateButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) childCreateButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-child-view.fxml"));
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

    @FXML
    private void onSignOutButtonClick() throws IOException {
        LoginController.loggedInUser = null;
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    private void onAssociatedWebsitesClick() throws IOException {
        Stage stage = (Stage) associateWebsitesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    private void onAboutButtonClick() throws IOException {
        Stage stage = (Stage) aboutbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    private void onChildAccountsButtonClick() throws IOException {
        Stage stage = (Stage) childAccountsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("childaccount-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
