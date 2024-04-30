package com.example.cab302groupnametbdproject;

import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainTableController implements Initializable {

        @FXML
        private TableColumn<MainTable, String> urllink;

        @FXML
        private TableColumn<MainTable, String> username;

        @FXML
        private TableColumn<MainTable, String> usertype;

        @FXML
        private TableColumn<MainTable, String> actions;

        @FXML
        private TableView<MainTable> datatable;

        @FXML
        private Button backbutton;

        @FXML
        private Button homebutton;

        @FXML
        private Button userbutton;

        private UserDAO userDAO;
        private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
        private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;

        // Constructor
        public MainTableController(){
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
                username.setCellValueFactory(new PropertyValueFactory<>("username"));
                usertype.setCellValueFactory(new PropertyValueFactory<>("usertype"));

                // Populate your table with dummy data
                List<Button> buttons1 = Arrays.asList(new Button("Disassociate"), new Button("Change"));
                List<Button> buttons2 = Arrays.asList(new Button("Disassociate"));
                List<Button> buttons3 = Arrays.asList(new Button("Disassociate"), new Button("Change"));


                populateTable();

        }

        public void populateTable(){

                // Get all passwords
                List<Password> passwords = PasswordDAO.getAllPasswords();

                // Buttons
                List<Button> buttons = new ArrayList<>();

                // Iterate once per Password in DB:
                for(Password password : passwords){
                        // Create User object based on the associated User of the Password
                        User user = userDAO.getUser(password.getUser_id());

                        // Create Website object based on the associated Website of the Password
                        Website website = AssociatedWebsiteDAO.getWebsite(password.getWebsite_id());

                        // Create Password object based on current iteration
                        Password cPassword = PasswordDAO.getPassword(password.getId());

                        buttons.add(new Button("Disassociate"));
                        buttons.add(new Button("Change"));

                        // Return datatable with user's username, website's URL, and password's content, buttons
                        datatable.getItems().add(new MainTable(website.getURL(), user.getUsername(), user.getUserType(), buttons));

                }


        }

}
