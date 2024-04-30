package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.PasswordDAO;
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
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;

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

    private User loggedInUser;


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

    public void addTestingData(){
        User user1 = new User("PARENT", "AAA", "ANAME", "ALNAME", "A@EMAIL.COM", "AAA");
        User user2 = new User("PARENT", "BBB", "BNAME", "BLNAME", "B@EMAIL.COM", "BBB");
        User user3 = new User("PARENT", "CCC", "CNAME", "CLNAME", "C@EMAIL.COM", "CCC");
        User user4 = new User("PARENT", "DDD", "DNAME", "DLNAME", "D@EMAIL.COM", "DDD");

        Password password1 = new Password(1, 2, "AAAAA");
        Password password2 = new Password(3, 3, "BBBBB");
        Password password3 = new Password(2, 2, "CCCCC");
        Password password4 = new Password(1, 1, "DDDDD");

        Website website1 = new Website("A.COM");
        Website website2 = new Website("B.COM");
        Website website3 = new Website("C.COM");
        Website website4 = new Website("D.COM");

        userDAO.addUser(user1);
        userDAO.addUser(user2);
        userDAO.addUser(user3);
        userDAO.addUser(user4);

        PasswordDAO.addPassword(password1);
        PasswordDAO.addPassword(password2);
        PasswordDAO.addPassword(password3);
        PasswordDAO.addPassword(password4);

        AssociatedWebsiteDAO.addWebsite(website1);
        AssociatedWebsiteDAO.addWebsite(website2);
        AssociatedWebsiteDAO.addWebsite(website3);
        AssociatedWebsiteDAO.addWebsite(website4);
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

        addTestingData();

        String usernameInput = username.getText();
        String passwordInput = password.getText();
        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            loginInfo.setText("Make sure all fields are filled.");
        } else {
            SqliteUserDAO table = new SqliteUserDAO();
            User userQuery = table.getUserFromUserName(usernameInput);
            if (userQuery != null) {
                if (passwordInput.equals(userQuery.getPassword())) {

                    // Set new frame of main screen if logged in
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);

                    // Create User object of logged in User
                    loggedInUser = userQuery;
                    loggedInUser.setKey(passwordInput);
                } else { loginInfo.setText("Incorrect Password."); }
            } else { loginInfo.setText("Username not found."); }
        }
    }



}
