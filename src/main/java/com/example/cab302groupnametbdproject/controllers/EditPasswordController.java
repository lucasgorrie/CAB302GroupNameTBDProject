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
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;
import static com.example.cab302groupnametbdproject.controllers.AssociatedWebsitesController.passwordEditing;


/**
 * Controller for edit password page
 */
public class EditPasswordController implements Initializable {
    @FXML
    public Label Title;
    @FXML
    private TextField password;
    @FXML
    private Button userbutton;
    @FXML
    private Button editPasswordButton;
    @FXML
    private Label infoLabel;


    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;

    // Constructor
    public EditPasswordController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    // Init
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());

        // Tell user what website they're editing the association for
        String editing_pass_url = AssociatedWebsiteDAO.getWebsite(passwordEditing.getWebsite_id()).getURL();
        String title_text = String.format("Editing Password for %s", editing_pass_url);
        Title.setText(title_text);
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


    /**
     * Button to redirect back to home page
     */
    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) editPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("associated-websites-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    /**
     * Takes info from password textfield and updates password in the database
     */
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


    /**
     * Generates a random, secure password and updates it in the database
     */
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


    /**
     * Method called when updating password in the DB. Uses user key to encrypt given password. Also adds URL to associated websites if not already there
     * @param addingPassword password to be added to DB
     * @param URL URL that password is to be associated with
     */
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
