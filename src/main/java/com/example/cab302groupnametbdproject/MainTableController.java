package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.cab302groupnametbdproject.LoginController.loggedInUser;

public class MainTableController implements Initializable {

        @FXML
        private TableColumn<MainTable, String> urllink;

        @FXML
        private TableColumn<MainTable, String> user;

        @FXML
        private TableColumn<MainTable, String> password;

        @FXML
        private TableColumn<MainTable, String> actions;

        @FXML
        private TableView<MainTable> datatable;

        @FXML
        private Button backToMenuButton;


        @FXML
        protected void onBackToMenuClick() throws IOException {
                Stage stage = (Stage) backToMenuButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
        }

        @FXML
        private Button homebutton;

        @FXML
        private Button userbutton;

        private UserDAO userDAO;
        private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
        private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;

        // Constructor
        public MainTableController() {
                userDAO = new SqliteUserDAO();
                AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
                PasswordDAO = new SqlitePasswordDAO();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                actions.setCellFactory(column -> new TableCell<MainTable, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                } else {
                                        MainTable user = getTableView().getItems().get(getIndex());
                                        List<Button> buttons = user.getActions();
                                        HBox hbox = new HBox();
                                        hbox.setSpacing(5);
                                        for (Button button : buttons) {
                                                button.setOnAction(event -> {
                                                        // Show an alert or perform any action here
                                                        System.out.println("Button clicked: " + button.getText());
                                                });
                                                hbox.getChildren().add(button);
                                        }
                                        setGraphic(hbox);
                                }
                        }
                });
                // Set cell value factories for other columns
                urllink.setCellValueFactory(new PropertyValueFactory<>("urllink"));
                user.setCellValueFactory(new PropertyValueFactory<>("user"));
                password.setCellValueFactory(new PropertyValueFactory<>("password"));

                populateTable();

        }

        public void populateTable() {

                // Get all passwords
                List<Password> passwords = PasswordDAO.getAllPasswords();


                // Iterate once per Password in DB:
                for (Password password : passwords) {
                        if(password.getUser_id() == loggedInUser.getId()) {
                                // Create User object based on the associated User of the Password
                                User user = userDAO.getUser(password.getUser_id());

                                // Create Website object based on the associated Website of the Password
                                Website website = AssociatedWebsiteDAO.getWebsite(password.getWebsite_id());

                                // Create Password object based on current iteration
                                Password cPassword = PasswordDAO.getPassword(password.getId());
                                String decryptedPassword = Encryption.decrypt(cPassword.getPasswordContent(), loggedInUser.getKey());

                                // Buttons
                                List<Button> buttons = new ArrayList<>();
                                buttons.add(new Button("Remove"));
                                buttons.add(new Button("Edit"));

                                // Return datatable with user's username, website's URL, and password's content, buttons
                                datatable.getItems().add(new MainTable(website.getURL(), user.getUsername(), decryptedPassword, buttons));
                        }
                }
        }

}
