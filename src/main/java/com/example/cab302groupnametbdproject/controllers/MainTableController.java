package com.example.cab302groupnametbdproject.controllers;

import com.example.cab302groupnametbdproject.HelloApplication;
import com.example.cab302groupnametbdproject.model.MainTable;
import com.example.cab302groupnametbdproject.model.associatedWebsites.SqliteAssociatedWebsiteDAO;
import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.users.SqliteUserDAO;
import com.example.cab302groupnametbdproject.model.users.User;
import com.example.cab302groupnametbdproject.model.users.UserDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.cab302groupnametbdproject.controllers.LoginController.loggedInUser;


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
        private Button userbutton;
        // Global password object for editing purposes
        public static Password passwordEditing;

        // Constructor
        private UserDAO userDAO;
        private com.example.cab302groupnametbdproject.model.associatedWebsites.AssociatedWebsiteDAO AssociatedWebsiteDAO;
        private com.example.cab302groupnametbdproject.model.passwords.PasswordDAO PasswordDAO;
        public MainTableController() {
                userDAO = new SqliteUserDAO();
                AssociatedWebsiteDAO = new SqliteAssociatedWebsiteDAO();
                PasswordDAO = new SqlitePasswordDAO();
        }


        // Remove password button method
        private void onRemoveButtonClick(Password password) throws IOException {
                String delete_URL = AssociatedWebsiteDAO.getWebsite(password.getWebsite_id()).getURL();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Password for " + delete_URL  + " ?",
                        ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                        //do stuff

                        PasswordDAO.deletePassword(password);

                        // Re-render page
                        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-datatable.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setScene(scene);
                }
        }


        // Copy password button method
        private void onCopyButtonClick(Password password) throws IOException {
                String decryptedPassword;
                if (userDAO.getUser(password.getUser_id()).getId() != loggedInUser.getId()) {
                        decryptedPassword = "[HIDDEN]";
                } else {
                        Password cPassword = PasswordDAO.getPassword(password.getId());
                        decryptedPassword = Encryption.decrypt(cPassword.getPasswordContent(), loggedInUser.getKey());
                }
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent clipboardPassword = new ClipboardContent();
                clipboardPassword.putString(decryptedPassword);
                clipboard.setContent(clipboardPassword);
        }


        // Edit password button method
        private void onEditButtonClick(Password password) throws IOException {
                passwordEditing = password; // Pass obj to be edited
                Stage stage = (Stage) backToMenuButton.getScene().getWindow();
                FXMLLoader fxmlLoader;
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-password-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
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


        // Init
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                userbutton.setText(loggedInUser.getUsername());
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


        // Add rows to main data table
        public void populateTable() {

                // Get all passwords
                List<Password> passwords = PasswordDAO.getAllPasswords();
                // Iterate once per Password in DB:
                for (Password password : passwords) {
                        // Only get user and user's child's passwords
                        if((password.getUser_id() == loggedInUser.getId()) || (userDAO.getUser(password.getUser_id())).getParentId() == loggedInUser.getId()) {

                                // Create User object based on the associated User of the Password
                                User user = userDAO.getUser(password.getUser_id());

                                // Create Website object based on the associated Website of the Password
                                Website website = AssociatedWebsiteDAO.getWebsite(password.getWebsite_id());

                                // Create Password object based on current iteration
                                String decryptedPassword;
                                if (userDAO.getUser(password.getUser_id()).getId() != loggedInUser.getId()) {
                                     decryptedPassword = "[HIDDEN]";
                                } else {
                                        Password cPassword = PasswordDAO.getPassword(password.getId());
                                        decryptedPassword = Encryption.decrypt(cPassword.getPasswordContent(), loggedInUser.getKey());
                                }

                                List<Button> buttons = new ArrayList<>();
                                // Button for password deletion
                                Button removeButton = new Button("Remove");
                                removeButton.setOnAction(event -> {
                                    try {
                                        onRemoveButtonClick(password);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                                buttons.add(removeButton);

                                // Additional IF so that these buttons do not appear for child entries
                                if(password.getUser_id() == loggedInUser.getId()) {

                                        // Button for password copy/paste
                                        Button copyButton = new Button("Copy");
                                        copyButton.setOnAction(event -> {
                                                try {
                                                        onCopyButtonClick(password);
                                                } catch (IOException e) {
                                                        throw new RuntimeException(e);
                                                }
                                        });
                                        buttons.add(copyButton);

                                        // Button for password editing
                                        Button editButton = new Button("Edit");
                                        editButton.setOnAction(event -> {
                                                try {
                                                        onEditButtonClick(password);
                                                } catch (IOException e) {
                                                        throw new RuntimeException(e);
                                                }
                                        });
                                        buttons.add(editButton);
                                }

                                // Return datatable with user's username, website's URL, and password's content, buttons
                                datatable.getItems().add(new MainTable(website.getURL(), user.getUsername(), decryptedPassword, buttons));
                        }
                }
        }
}
