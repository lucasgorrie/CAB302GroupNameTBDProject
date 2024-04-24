package com.example.cab302groupnametbdproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

        @FXML
        private TableColumn<User, String> actionscolumn;

        @FXML
        private Button backbutton;

        @FXML
        private TableView<User> datatable;

        @FXML
        private TableColumn<User, String> urlcolumn;

        @FXML
        private Button userbutton;

        @FXML
        private TableColumn<User, String> usercolumn;

        @FXML
        private TableColumn<User, String> usertypecolumn;

        ObservableList<User> list = FXCollections.observableArrayList(
                new User("https://www.example.com/page.html", "User0", "Parent", "Disassociate"),
                new User("https://www.example.com/page.html", "User0Child1", "Child", "Disassociate"),
                new User("https://www.example.com/page.html", "User1", "Parent", "Disassociate")
        );


    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                urlcolumn.setCellValueFactory(new PropertyValueFactory<User, String>("urlcolumn"));
                usercolumn.setCellValueFactory(new PropertyValueFactory<User, String>("usercolumn"));
                usertypecolumn.setCellValueFactory(new PropertyValueFactory<User, String>("usertypecolumn"));
                actionscolumn.setCellValueFactory(new PropertyValueFactory<User, String>("actionscolumn"));

                datatable.setItems(list);
        }


}
