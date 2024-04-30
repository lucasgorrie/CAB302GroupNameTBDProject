package com.example.cab302groupnametbdproject;

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
        private Button backToMenuButton;

        @FXML
        private Button homebutton;

        @FXML
        private Button userbutton;

        @FXML
        protected void onBackToMenuClick() throws IOException {
                Stage stage = (Stage) backToMenuButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
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
                                        MainTable mainTable = getTableView().getItems().get(getIndex());
                                        List<Button> buttons = mainTable.getActions();
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

                datatable.getItems().addAll(
                        new MainTable("https://www.example.com/page.html", "User0", "Parent", buttons1),
                        new MainTable("https://www.example.com/page.html", "User0Child1", "Child", buttons2),
                        new MainTable("https://www.example.com/page.html", "User1", "Parent", buttons3)
                );
        }
}
