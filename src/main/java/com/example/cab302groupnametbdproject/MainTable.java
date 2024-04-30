package com.example.cab302groupnametbdproject;

import javafx.scene.control.Button;
import java.util.List;

public class MainTable {

    /* Columns for Main Table */
    private String urllink;
    private String username;
    private String usertype;
    private List<Button> actions;

    public MainTable(String urllink, String username, String usertype, List<Button> actions) {
        this.urllink = urllink;
        this.username = username;
        this.usertype = usertype;
        this.actions = actions;

    }

    /* Getters */

    public String getUrllink() {
        return urllink;
    }

    public String getUsername() {
        return username;
    }

    public String getUsertype() {
        return usertype;
    }

    public List<Button> getActions() {
        return actions;
    }
}
