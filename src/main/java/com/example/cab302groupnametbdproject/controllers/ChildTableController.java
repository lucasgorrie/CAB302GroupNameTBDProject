package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.model.tables.ChildTable;
import com.example.cab302groupnametbdproject.PasswordMangerMain;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


/**
 * Controller for child table page
 */
public class ChildTableController implements Initializable {

    @FXML
    private TableColumn<ChildTable,Integer> associations;
    @FXML
    private Button backToMenuButton;
    @FXML
    private TableColumn<ChildTable, Integer> childno;
    @FXML
    private TableView<ChildTable> childtable;
    @FXML
    private TableColumn<ChildTable, String> user2;
    @FXML
    private Button userbutton;


    // Constructor
    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
    public ChildTableController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userbutton.setText(loggedInUser.getUsername());
        user2.setCellValueFactory(new PropertyValueFactory<>("user2"));
        childno.setCellValueFactory(new PropertyValueFactory<>("userno"));
        associations.setCellValueFactory(new PropertyValueFactory<>("associations"));

        List<User> all_users = userDAO.getAllUsers();
        List<User> child_users = null;

        // Child_users should be a list of all child accounts associated with the logged user

        populateTable();
    }

    /**
     * Redirects to user page
     */
    @FXML
    protected void onUserButtonClick() throws IOException {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("user-info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Redirects back to home page
     */
    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordMangerMain.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Returns the total number of associated websites for a particular user based on an ID arg
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
     * Adds child accounts to the table rendered on the page
     */
    public void populateTable() {

        // Get all passwords
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUserType().equals("CHILD")) {
                if (user.getParentId() == loggedInUser.getId()) {
                    childtable.getItems().add(new ChildTable(user.getUsername(), user.getId(), TotalAssociations(user.getId())));
                }
            }
        }
    }
}
