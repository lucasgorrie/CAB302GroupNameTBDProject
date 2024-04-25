package com.example.cab302groupnametbdproject;

import javafx.scene.control.Button;
import java.util.List;

public class User {

    /* Columns for Main Table */
    private String urllink;
    private String user;
    private String usertype;
    private List<Button> actions;

    public User(String urllink, String user, String usertype, List<Button> actions) {
        this.urllink = urllink;
        this.user = user;
        this.usertype = usertype;
        this.actions = actions;

    }

    /* Getters */

    public String getUrllink() {
        return urllink;
    }

    public String getUser() {
        return user;
    }

    public String getUsertype() {
        return usertype;
    }

    public List<Button> getActions() {
        return actions;
    }
}
