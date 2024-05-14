package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.ChildTable;
import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
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

    @FXML
    private Button userbutton1;

    private UserDAO userDAO;
    private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
    private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;

    public ChildTableController(){
        userDAO = new SqliteUserDAO();
        AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
        PasswordDAO = new SqlitePasswordDAO();
    }

    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user2.setCellValueFactory(new PropertyValueFactory<>("user2"));
        childno.setCellValueFactory(new PropertyValueFactory<>("userno"));
        associations.setCellValueFactory(new PropertyValueFactory<>("associations"));

        List<User> all_users = userDAO.getAllUsers();
        List<User> child_users = null;

        // Child_users should be a list of all child accounts associated with the logged user
        for(int i = 0; i < all_users.size(); i++){

            if(all_users.get(i).getParentId() == loggedInUser.getId()) {
                child_users.add(all_users.get(i));
            }
        }

        childtable.getItems().addAll(
                new ChildTable("User0Child0", 1, 13),
                new ChildTable("User0Child1", 2, 6)
                );
    }

}
