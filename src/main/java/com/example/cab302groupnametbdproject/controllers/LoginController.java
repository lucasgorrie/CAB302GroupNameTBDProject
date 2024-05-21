package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.cab302groupnametbdproject.model.users.User;
import org.apache.commons.codec.digest.DigestUtils;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private Button createUserButton;

    @FXML
    private Label loginInfo;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    public static User loggedInUser;


    // Constructor
    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
    public LoginController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    public SqliteUserDAO getUserDAO(){
        return (SqliteUserDAO) this.userDAO;
    }

    public SqliteAssociatedWebsiteDAO getWebsiteDAO(){
        return (SqliteAssociatedWebsiteDAO) this.AssociatedWebsiteDAO;
    }

    public SqlitePasswordDAO getPasswordDAO(){
        return (SqlitePasswordDAO) this.PasswordDAO;
    }


    //button to take user to signup view when hitting create user button
    @FXML
    protected void onCreateUserButtonClick() throws IOException {
        Stage stage = (Stage) createUserButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    protected void onLoginButtonClick() throws IOException {


        String usernameInput = username.getText();
        String passwordInput = password.getText();
        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            loginInfo.setText("Make sure all fields are filled.");
        } else {
            SqliteUserDAO table = new SqliteUserDAO();
            User userQuery = table.getUserFromUserName(usernameInput);
            if (userQuery != null) {
                String hashedPasswordInput = DigestUtils.sha256Hex(passwordInput + usernameInput);
                if (hashedPasswordInput.equals(userQuery.getPassword())) {

                    // Set new frame of main screen if logged in
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    FXMLLoader fxmlLoader;
                    if (userQuery.getUserType().equals("PARENT")) {
                        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    } else {
                        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("child-interface-view.fxml"));
                    }
                    // Set logged in User
                    loggedInUser = userQuery;
                    loggedInUser.setKey(passwordInput);
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);

                } else { loginInfo.setText("Incorrect Password."); }
            } else { loginInfo.setText("Username not found."); }
        }
    }



}
