package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.PasswordMangerMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


/**
 * Controller for update user details page
 */
public class UpdateDetailsController {

    public Label signupInfo;
    @FXML
    public Button updateUser;
    @FXML
    private Button backToInfo;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;

    //Regex to validate info
    private final int MIN_USERNAME_CHARACTERS = 6;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    /**
     * Check that string arg matches email regex
     * @return True if valid, false if not
     */
    private boolean validateEmail(String email_string){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email_string);
        return matcher.matches();
    }


    /**
     * Redirects back to user page
     */
    @FXML
    protected void onBackToInfoClick() throws IOException {
        Stage stage = (Stage) backToInfo.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("user-info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Validates entered information and updates database
     */
    @FXML
    protected void UpdateUserButtonClick() throws IOException {
        SqliteUserDAO table = new SqliteUserDAO(); // DAO instantiated here rather than in constructor
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();
        String usernameInput = username.getText();
        String emailInput = email.getText();

        // Check that all fields are filled
        if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || usernameInput.isEmpty() || emailInput.isEmpty()) {
            signupInfo.setText("Make sure all fields are filled.");

            // Check that the username does not already exist
        } else if(table.doesUsernameExist(usernameInput)) {
            signupInfo.setText("Username already registered");

            // Check that the minimum character requirement for usernames is met
        } else if(usernameInput.length() < MIN_USERNAME_CHARACTERS) {
            signupInfo.setText("Username must be at least 6 characters long");

            // Check that email input matches email regex
        } else if(!validateEmail(emailInput)) {
            signupInfo.setText("Must be a valid email");

            // Update user
        } else {
            loggedInUser.setFirstName(firstNameInput);
            loggedInUser.setLastName(lastNameInput);
            loggedInUser.setUsername(usernameInput);
            loggedInUser.setEmail(emailInput);
            table.updateUser(loggedInUser);
            onBackToInfoClick();
        }
    }
}
