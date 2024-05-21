package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;

import java.io.IOException;
import java.util.ResourceBundle;

import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;

import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;

public class AddPasswordController implements Initializable {
    @FXML
    private TextField password;
    @FXML
    private TextField URL;
    @FXML
    private Button userbutton;
    @FXML
    private Button addPasswordButton;
    @FXML
    private Button generatePasswordButton;

    @FXML
    private Button backToMenuButton;
    @FXML
    private Label infoLabel;

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        if (LoginController.loggedInUser.getUserType().equals("PARENT")) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("child-interface-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onAddPasswordClick() throws IOException {
        String passwordInput = password.getText();
        String URLInput = URL.getText();
        if (passwordInput.isEmpty() || URLInput.isEmpty()) {
            infoLabel.setText("Make sure all fields are filled.");
        } else {
            newPassword(passwordInput, URLInput);
            onBackToMenuClick();
        }
    }
    @FXML
    protected void onGeneratePasswordClick() throws IOException {
        String URLInput = URL.getText();
        if (URLInput.isEmpty()) {
            infoLabel.setText("URL field is empty.");
        } else {
            String generatedPassword = "";
            char[] Characters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                    'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    '0','1', '2', '3', '4', '5', '6', '7', '8', '9',
                    '!', '@', '#', '$', '%', '&' };
            for (int i = 0; i < 15; i++) {
                Random random = new Random();
                generatedPassword += Characters[random.nextInt(Characters.length)];
            }
            newPassword(generatedPassword, URLInput);
            onBackToMenuClick();
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
        String encryptedPassword = Encryption.encrypt(addingPassword, LoginController.loggedInUser.getKey());
        Password newPassword = new Password(LoginController.loggedInUser.getId(), website.getId(), encryptedPassword);
        passwordTable.addPassword(newPassword);

    }


}
