package com.example.cab302groupnametbdproject;

public class User {
    private final String urllink;
    private final String user;
    private final String usertype;
    private final String actions;

    public User(String urllink, String user, String usertype, String actions) {
        this.urllink = urllink;
        this.user = user;
        this.usertype = usertype;
        this.actions = actions;
    }

    public String getUrllink() {
        return urllink;
    }

    public String getUser() {
        return user;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getActions() {
        return actions;
    }
}
