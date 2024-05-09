package com.example.cab302groupnametbdproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.cab302groupnametbdproject.model.passwords.Password;
import com.example.cab302groupnametbdproject.model.passwords.SqlitePasswordDAO;
import com.example.cab302groupnametbdproject.model.passwords.Encryption;

public class AddPasswordController {
    @FXML
    private TextField password;
    @FXML
    private Button addPasswordButton;
    @FXML
    private Button generatePasswordButton;

    @FXML
    private Button backToMenuButton;
    @FXML
    private Label infoLabel;

    @FXML
    protected void onBackToMenuClick() throws IOException {
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-datatable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onAddPasswordClick() throws IOException {
        String passwordInput = password.getText();
        if (passwordInput.isEmpty()) {
            infoLabel.setText("Password Field Empty");
        } else {
            newPassword(passwordInput);
        }
    }
    private void newPassword(String addingPassword) {
        SqlitePasswordDAO db = new SqlitePasswordDAO();
        int user_id = 0;
        int website_id = 0;
        String key = "Test Key Innit";
        String encryptedPassword = Encryption.encrypt(addingPassword, key);
        Password newPassword = new Password(user_id, website_id, encryptedPassword);
        db.addPassword(newPassword);

    }


}
