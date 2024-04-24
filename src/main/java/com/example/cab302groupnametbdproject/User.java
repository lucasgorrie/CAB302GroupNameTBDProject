package com.example.cab302groupnametbdproject;

public class User {
    private String urlcolumn;
    private String usercolumn;
    private String usertypecolumn;
    private String actionscolumn;

    public User(String urlcolumn, String usercolumn, String usertypecolumn, String actionscolumn) {
        this.urlcolumn = urlcolumn;
        this.usercolumn = usercolumn;
        this.usertypecolumn = usertypecolumn;
        this.actionscolumn = actionscolumn;
    }

    public String getUrlcolumn() {
        return urlcolumn;
    }

    public String getUsercolumn() {
        return usercolumn;
    }

    public String getUsertypecolumn() {
        return usertypecolumn;
    }

    public String getActionscolumn() {
        return actionscolumn;
    }
}
