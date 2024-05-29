package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


public class UserInfoController implements Initializable {

    @FXML
    public Button backToMenuButton;
    @FXML
    public Button userbutton;
    @FXML
    public Button homebutton;
    @FXML
    public Text nametext;
    @FXML
    public Text emailtext;
    @FXML
    public Text usernametext;
    @FXML
    public Text associationsnum;
    @FXML
    public Text childaccountsnum;
    @FXML
    public Button changedetailsbutton;
    @FXML
    public Button changepasswordbutton;


    // Constructor
    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
    public UserInfoController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    // Initialize display data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
        nametext.setText(loggedInUser.getFullName());
        emailtext.setText(loggedInUser.getEmail());
        usernametext.setText(loggedInUser.getUsername());

        String associations = String.valueOf(TotalAssociations(loggedInUser.getId()));
        String children = String.valueOf(TotalChildAccounts(loggedInUser.getId()));

        associationsnum.setText(associations);
        childaccountsnum.setText(children);
    }

    public int TotalAssociations(int user_id){
        List<Password> allPass = PasswordDAO.getAllPasswords();
        int association_count = 0;
        for (Password pass : allPass) {
            if (pass.getUser_id() == user_id) {
                association_count++;
            }
        }
        return association_count;
    }

    public int TotalChildAccounts(int user_id){
        List<User> allUser = userDAO.getAllUsers();
        int child_count = 0;
        for(User user : allUser){
            if(user.getParentId() == user_id){
                child_count++;
            }
        }
        return child_count;
    }

    // Back button method
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

    // Change details button method
    @FXML
    protected void changeDetailsButtonClick() throws IOException {
        Stage stage = (Stage) changedetailsbutton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("update-details-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    // Change password button method
    @FXML
    protected void changePasswordButtonClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("update-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
