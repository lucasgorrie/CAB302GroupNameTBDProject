package com.example.cab302groupnametbdproject.controllers;
import com.example.cab302groupnametbdproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserController {

    public Label signupInfo;
    @FXML
    private Button backToLoginButton;
    @FXML
    private Button createUser;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField repeatPassword;

    private final int MIN_USERNAME_CHARACTERS = 6;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\!\"#$%&'()*+,-./:;<=>?@\\]\\[^_`{|}~])(?=\\S+$).{8,}$");

    private boolean validateEmail(String email_string){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email_string);
        return matcher.matches();
    }

    private boolean validatePassword(String password_string){
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password_string);
        return matcher.matches();
    }

    //button to take the user back to the login page from the signup page
    @FXML
    protected void onBackToLoginClick() throws IOException {
        Stage stage = (Stage) backToLoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    protected void createUserClick() throws IOException {
        SqliteUserDAO table = new SqliteUserDAO();
        int parent_id = 0; // <-- NOT A CHILD
        String user_type = "PARENT";
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();
        String usernameInput = username.getText();
        String emailInput = email.getText();
        String passwordInput = password.getText();
        String repeatPasswordInput = repeatPassword.getText();

        // Check that all fields are filled
        if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || usernameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || repeatPasswordInput.isEmpty()) {
            signupInfo.setText("Make sure all fields are filled.");

        // Check that passwords match
        } else if (!passwordInput.equals(repeatPasswordInput)) {
            signupInfo.setText("Passwords do not match.");

        // Check that the username does not already exist
        } else if(table.doesUsernameExist(usernameInput)) {
            signupInfo.setText("Username already registered");

        // Check that the minimum character requirement for usernames is met
        } else if(usernameInput.length() < MIN_USERNAME_CHARACTERS) {
            signupInfo.setText("Username must be at least 6 characters long");

        // Check that email input matches email regex
        } else if(!validateEmail(emailInput)) {
            signupInfo.setText("Must be a valid email");

        // Check that password input matches password regex
        } else if(!validatePassword(passwordInput)){
            signupInfo.setText("Password must be mixed case, contain a number and a special character, and be of at least 8 characters");

        // Enter user into DB
        } else {
            String passwordHash = DigestUtils.sha256Hex(passwordInput + usernameInput);
            User newUser = new User(user_type, usernameInput, firstNameInput, lastNameInput, emailInput, passwordHash);
            table.addUser(newUser);
            onBackToLoginClick();
            signupInfo.setText("User Created");
        }

    }


}
