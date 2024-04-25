package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainTableController implements Initializable {
        @FXML
        private TableColumn<User, String> urllink;

        @FXML
        private TableColumn<User, String> user;

        @FXML
        private TableColumn<User, String> usertype;

        @FXML
        private TableColumn<User, String> actions;

        @FXML
        private TableView<User> datatable;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                actions.setCellFactory(column -> new TableCell<User, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                } else {
                                        User user = getTableView().getItems().get(getIndex());
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
                usertype.setCellValueFactory(new PropertyValueFactory<>("usertype"));

                // Populate your table with dummy data
                List<Button> buttons1 = Arrays.asList(new Button("Disassociate"), new Button("Change"));
                List<Button> buttons2 = Arrays.asList(new Button("Disassociate"));
                List<Button> buttons3 = Arrays.asList(new Button("Disassociate"), new Button("Change"));

                datatable.getItems().addAll(
                        new User("https://www.example.com/page.html", "User0", "Parent", buttons1),
                        new User("https://www.example.com/page.html", "User0Child1", "Child", buttons2),
                        new User("https://www.example.com/page.html", "User1", "Parent", buttons3)
                );
        }
}
