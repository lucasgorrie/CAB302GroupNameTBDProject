package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private BorderPane mainLayout;
    @FXML
    private VBox mainContent;

    private void switchToPage(String labelText) {
        Label pageLabel = new Label(labelText);
        pageLabel.setAlignment(Pos.CENTER);

        VBox centerContent = new VBox(pageLabel);
        centerContent.setAlignment(Pos.CENTER);

        // Use the custom BackButton class
        BackButton backButton = new BackButton(mainLayout, mainContent);

        BorderPane newPage = new BorderPane();
        newPage.setTop(backButton);
        newPage.setCenter(centerContent);

        mainLayout.setCenter(newPage);
    }

    public static class BackButton extends Button {

        public BackButton(BorderPane mainLayout, Pane mainContent) {
            super("Back");
            this.setOnAction(event -> mainLayout.setCenter(mainContent));
            this.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
            BorderPane.setMargin(this, new Insets(10));
            BorderPane.setAlignment(this, Pos.TOP_RIGHT);
        }
    }
    @FXML
    private void onSignOutButtonClick() {
        switchToPage("Sign Out");
    }

    @FXML
    private void onAssociatedWebsitesClick() {
        switchToPage("Associated Websites");
    }

    @FXML
    private void onAboutButtonClick() {
        switchToPage("About Us");
    }

    @FXML
    private void onChildAccountsButtonClick() {
        switchToPage("Child Accounts");
    }
}
