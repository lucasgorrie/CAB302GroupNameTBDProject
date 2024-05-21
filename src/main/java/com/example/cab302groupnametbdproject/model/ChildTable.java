package com.example.cab302groupnametbdproject.model;

public class ChildTable {

    /* Columns for Child Table */

    private String user2;

    private int userno;

    private int associations;

    public ChildTable(String user2, int userno, int associations) {
        this.user2 = user2;
        this.userno = userno;
        this.associations = associations;
    }

    /* Getters */

    public String getUser2() {
        return user2;
    }

    public int getUserno() {
        return userno;
    }

    public int getAssociations() {
        return associations;
    }
}
