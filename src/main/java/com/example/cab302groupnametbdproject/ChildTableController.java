package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChildTableController implements Initializable {

    @FXML
    private TableColumn<User,Integer> associations;

    @FXML
    private Button backbutton;

    @FXML
    private TableColumn<User, Integer> childno;

    @FXML
    private TableView<User> datatable;

    @FXML
    private TableColumn<User, String> user2;

    @FXML
    private Button userbutton;

    @FXML
    private Button userbutton1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
