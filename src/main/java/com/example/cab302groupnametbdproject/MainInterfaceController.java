package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class MainInterfaceController {

    @FXML
    private BorderPane mainLayout;
    @FXML
    private VBox mainContent;

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
