package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
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
import static com.example.cab302groupnametbdproject.controllers.MainTableController.passwordEditing;

public class EditPasswordController implements Initializable {
    @FXML
    private TextField password;
    @FXML
    private Button userbutton;
    @FXML
    private Button editPasswordButton;
    @FXML
    private Button generatePasswordButton;
    @FXML
    private Label infoLabel;

    // Constructor
    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
    public EditPasswordController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) editPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onEditPasswordClick() throws IOException {
        String URLInput = AssociatedWebsiteDAO.getWebsite(passwordEditing.getWebsite_id()).getURL();
        String passwordInput = password.getText();
        if (passwordInput.isEmpty() || URLInput.isEmpty()) {
            infoLabel.setText("Make sure all fields are filled.");
        } else {
            editPassword(passwordInput, URLInput);
            onBackToMenuClick();
        }
    }
    @FXML
    protected void onGeneratePasswordClick() throws IOException {
        String URLInput = AssociatedWebsiteDAO.getWebsite(passwordEditing.getWebsite_id()).getURL();
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
            editPassword(generatedPassword, URLInput);
            onBackToMenuClick();
        }


    }
    private void editPassword(String addingPassword, String URL) {
        Website website = AssociatedWebsiteDAO.getWebsiteFromURL(URL);
        if (website == null) {
            website = new Website(URL);
            AssociatedWebsiteDAO.addWebsite(website);
            website = AssociatedWebsiteDAO.getWebsiteFromURL(URL);
        }

        String encryptedPassword = Encryption.encrypt(addingPassword, loggedInUser.getKey());
        passwordEditing.setPassword(encryptedPassword); // Use same object with updated contents for same ID
        PasswordDAO.updatePassword(passwordEditing);

    }


}
