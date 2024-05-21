package com.example.cab302groupnametbdproject.model;

import javafx.scene.control.Button;
import java.util.List;

public class MainTable {

    /* Columns for Main Table */
    private String urllink;
    private String user;
    private String password;
    private List<Button> actions;

    public MainTable(String urllink, String user, String password, List<Button> actions) {
        this.urllink = urllink;
        this.user = user;
        this.password = password;
        this.actions = actions;

    }

    /* Getters */

    public String getUrllink() {
        return urllink;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public List<Button> getActions() {
        return actions;
    }
}
