package com.example.cab302groupnametbdproject;
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

import java.io.IOException;

import static com.example.cab302groupnametbdproject.LoginController.loggedInUser;

public class CreateChildController {

    public Label signupInfo;
    @FXML
    private Button backToMenuButton;
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


    //button to take the user back to the login page from the signup page
    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    protected void createChildClick() throws IOException {
        String user_type = "CHILD";
        int parent_id = loggedInUser.getId();
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();
        String usernameInput = username.getText();
        String emailInput = email.getText();
        String passwordInput = password.getText();
        String repeatPasswordInput = repeatPassword.getText();
        if (firstNameInput.isEmpty() || lastNameInput.isEmpty() || usernameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() || repeatPasswordInput.isEmpty()) {
            signupInfo.setText("Make sure all fields are filled.");
        } else if (!passwordInput.equals(repeatPasswordInput)) {
            signupInfo.setText("Passwords do not match.");
        } else {
            SqliteUserDAO table = new SqliteUserDAO();
            User newUser = new User(user_type, usernameInput, firstNameInput, lastNameInput, emailInput, passwordInput);
            newUser.setParentId(parent_id);
            table.addUser(newUser);
            onBackToMenuClick();
            signupInfo.setText("Child Created");
        }

    }


}
