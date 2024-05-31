package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.PasswordMangerMain;
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
import java.util.Objects;
import java.util.ResourceBundle;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


/**
 * Controller for user info page
 */
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
    @FXML
    public Text childaccountstitle;


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

        // If account is child account do not show details relating to child accounts
        if(Objects.equals(loggedInUser.getUserType(), "CHILD")) {
            childaccountsnum.setText("");
            childaccountstitle.setText("");
        }

    }

    /**
     * Button to redirect user back to main menu
     */
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
     * Finds total number of associations for given user
     * @return number of association
     */
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

    /**
     * Finds total number of child accounts for given user
     * @return number of child accounts
     */
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

    /**
     * Button to redirect to update details page
     */
    @FXML
    protected void changeDetailsButtonClick() throws IOException {
        Stage stage = (Stage) changedetailsbutton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("update-details-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button to redirect to update password page
     */
    @FXML
    protected void changePasswordButtonClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("update-password-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
