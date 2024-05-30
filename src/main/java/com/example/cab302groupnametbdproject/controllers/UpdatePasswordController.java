package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


public class UpdatePasswordController {

    public Label signupInfo;
    @FXML
    public Button updatePassword;
    @FXML
    private Button backToInfoButton;
    @FXML
    private TextField password;
    @FXML
    private TextField repeatPassword;
    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\!\"#$%&'()*+,-./:;<=>?@\\]\\[^_`{|}~])(?=\\S+$).{8,}$");


    // Check that string arg matches password regex
    private boolean validatePassword(String password_string){
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password_string);
        return matcher.matches();
    }


    // Button to take the user back to the login page from the signup page
    @FXML
    protected void onBackToInfoClick() throws IOException {
        Stage stage = (Stage) backToInfoButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    // Update password button method
    @FXML
    protected void updatePasswordClick() throws IOException {
        SqliteUserDAO table = new SqliteUserDAO(); // DAO instantiated here rather than in constructor
        String passwordInput = password.getText();
        String repeatPasswordInput = repeatPassword.getText();

        // Check that all fields are filled
        if (passwordInput.isEmpty() || repeatPasswordInput.isEmpty()) {
            signupInfo.setText("Make sure all fields are filled.");

            // Check that passwords match
        } else if (!passwordInput.equals(repeatPasswordInput)) {
            signupInfo.setText("Passwords do not match.");

            // Check that password input matches password regex
        } else if(!validatePassword(passwordInput)){
            signupInfo.setText("Password must be mixed case, contain a number and" +
                    "\na special character, and be of at least 8 characters");

            // Enter user into DB
        } else {
            String passwordHash = DigestUtils.sha256Hex(passwordInput + loggedInUser.getUsername());
            loggedInUser.setPassword(passwordHash);
            table.updateUser(loggedInUser);
            onBackToInfoClick();
            signupInfo.setText("Password Updated");
        }
    }
}
