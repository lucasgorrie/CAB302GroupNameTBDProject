package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.PasswordMangerMain;
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
import java.util.Random;
import java.io.IOException;
import java.util.ResourceBundle;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


/**
 * Controller for adding password page
 */
public class AddPasswordController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField URL;
    @FXML
    private Button userbutton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Label infoLabel;

    // Init
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
    }

    // Constructor
    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
    public AddPasswordController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    /**
     * Redirects to home page
     */
    @FXML
    protected void onUserButtonClick() throws IOException {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("user-info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    // Back button method
    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        if (LoginController.loggedInUser.getUserType().equals("PARENT")) {
            fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("main-menu-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("child-mainmenu-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    /**
     * Takes info from password textfield and adds a new password from it
     */
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


    /**
     * Generates a random, secure password and adds it to the database
     */
    @FXML
    protected void onGeneratePasswordClick() throws IOException {
        String URLInput = URL.getText();
        if (URLInput.isEmpty()) {
            infoLabel.setText("URL field is empty.");
        } else {
            //picks random characters from chracter bank and creates password
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


    /**
     * Method called when adding password to DB. Uses user key to encrypt given password. Also adds URL to associated websites if not already there
     * @param addingPassword password to be added to DB
     * @param URL URL that password is to be associated with
     */
    private void newPassword(String addingPassword, String URL) {
        Website website = AssociatedWebsiteDAO.getWebsiteFromURL(URL);
        if (website == null) {
            website = new Website(URL);
            AssociatedWebsiteDAO.addWebsite(website);
            website = AssociatedWebsiteDAO.getWebsiteFromURL(URL);
        }
        //encrypts password
        String encryptedPassword = Encryption.encrypt(addingPassword, LoginController.loggedInUser.getKey());
        Password newPassword = new Password(LoginController.loggedInUser.getId(), website.getId(), encryptedPassword);
        PasswordDAO.addPassword(newPassword);

    }
}
