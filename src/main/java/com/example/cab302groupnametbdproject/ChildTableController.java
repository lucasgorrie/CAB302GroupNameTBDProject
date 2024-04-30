package com.example.cab302groupnametbdproject;

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
import java.util.ResourceBundle;

public class ChildTableController implements Initializable {

    @FXML
    private TableColumn<ChildTable,Integer> associations;

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
    private TableColumn<ChildTable, Integer> childno;

    @FXML
    private TableView<ChildTable> childtable;

    @FXML
    private TableColumn<ChildTable, String> user2;

    @FXML
    private Button userbutton;

    @FXML
    private Button userbutton1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user2.setCellValueFactory(new PropertyValueFactory<>("user2"));
        childno.setCellValueFactory(new PropertyValueFactory<>("userno"));
        associations.setCellValueFactory(new PropertyValueFactory<>("associations"));

        childtable.getItems().addAll(
                new ChildTable("User0Child0", 1, 13),
                new ChildTable("User0Child1", 2, 6)
                );
    }

}
