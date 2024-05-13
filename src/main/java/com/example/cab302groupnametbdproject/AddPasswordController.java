package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;

public class AddPasswordController {
    @FXML
    private TextField password;
    @FXML
    private TextField URL;
    @FXML
    private Button addPasswordButton;
    @FXML
    private Button generatePasswordButton;

    @FXML
    private Button backToMenuButton;
    @FXML
    private Label infoLabel;

    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onAddPasswordClick() throws IOException {
        String passwordInput = password.getText();
        String URLInput = URL.getText();
        if (passwordInput.isEmpty()) {
            infoLabel.setText("Password Field Empty");
        } else {
            newPassword(passwordInput, URLInput);
        }
    }
    private void newPassword(String addingPassword, String URL) {
        SqlitePasswordDAO passwordTable = new SqlitePasswordDAO();
        SqliteAssociatedWebsiteDAO websiteTable = new SqliteAssociatedWebsiteDAO();
        Website website = websiteTable.getWebsiteFromURL(URL);
        if (website == null) {
            website = new Website(URL);
            websiteTable.addWebsite(website);
            website = websiteTable.getWebsiteFromURL(URL);
        }
        String key = "Test Key Innit";
        String encryptedPassword = Encryption.encrypt(addingPassword, key);
        Password newPassword = new Password(LoginController.loggedInUser.getId(), website.getId(), encryptedPassword);
        passwordTable.addPassword(newPassword);

    }


}
