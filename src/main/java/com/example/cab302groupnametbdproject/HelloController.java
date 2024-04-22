package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class HelloController {

    @FXML
    private BorderPane mainLayout;
    @FXML
    private VBox mainContent;

    @FXML
    private void onSignOutButtonClick() {
        // Create a new BorderPane
        BorderPane SignOutButtonPage = new BorderPane();
        Label SignOutButtonLabel = new Label("Sign Out");
        SignOutButtonLabel.setAlignment(Pos.CENTER);

        // Create a VBox for central content
        VBox centerContent = new VBox(SignOutButtonLabel);
        centerContent.setAlignment(Pos.CENTER);

        // Back Button in the top right
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainLayout.setCenter(mainContent));

        // Setting the back button in the top right corner of the BorderPane
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10)); // Adds a margin around the button
        SignOutButtonPage.setTop(backButton);
        SignOutButtonPage.setCenter(centerContent);

        // Set the new page as the center of the main layout
        mainLayout.setCenter(SignOutButtonPage);
    }

    @FXML
    private void onAssociatedWebsitesClick() {
        // Create a new BorderPane
        BorderPane AssociatedWebsitesPage = new BorderPane();
        Label AssociatedWebsitesLabel = new Label("Associated Websites");
        AssociatedWebsitesLabel.setAlignment(Pos.CENTER);

        // Create a VBox for central content
        VBox centerContent = new VBox(AssociatedWebsitesLabel);
        centerContent.setAlignment(Pos.CENTER);

        // Back Button in the top right
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainLayout.setCenter(mainContent));

        // Setting the back button in the top right corner of the BorderPane
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10)); // Adds a margin around the button
        AssociatedWebsitesPage.setTop(backButton);
        AssociatedWebsitesPage.setCenter(centerContent);

        // Set the new page as the center of the main layout
        mainLayout.setCenter(AssociatedWebsitesPage);
    }

    @FXML
    private void onAboutButtonClick() {
        // Create a new BorderPane
        BorderPane AboutUsPage = new BorderPane();
        Label AboutUsLabel = new Label("About Us");
        AboutUsLabel.setAlignment(Pos.CENTER);

        // Create a VBox for central content
        VBox centerContent = new VBox(AboutUsLabel);
        centerContent.setAlignment(Pos.CENTER);

        // Back Button in the top right
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainLayout.setCenter(mainContent));

        // Setting the back button in the top right corner of the BorderPane
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10)); // Adds a margin around the button
        AboutUsPage.setTop(backButton);
        AboutUsPage.setCenter(centerContent);

        // Set the new page as the center of the main layout
        mainLayout.setCenter(AboutUsPage);
    }

    @FXML
    private void onChildAccountsButtonClick() {
        // Create a new BorderPane
        BorderPane childAccountsPage = new BorderPane();
        Label childAccountsLabel = new Label("Child Accounts");
        childAccountsLabel.setAlignment(Pos.CENTER);

        // Create a VBox for central content
        VBox centerContent = new VBox(childAccountsLabel);
        centerContent.setAlignment(Pos.CENTER);

        // Back Button in the top right
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainLayout.setCenter(mainContent));

        // Setting the back button in the top right corner of the BorderPane
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10)); // Adds a margin around the button
        childAccountsPage.setTop(backButton);
        childAccountsPage.setCenter(centerContent);

        // Set the new page as the center of the main layout
        mainLayout.setCenter(childAccountsPage);
    }
}
