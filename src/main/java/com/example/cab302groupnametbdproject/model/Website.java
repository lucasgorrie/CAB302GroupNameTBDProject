package com.example.cab302groupnametbdproject.model;

public class Website {
    private int id;
    private String URL;

    // Constructor
    public Website(String URL){
        this.URL = URL;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getURL(){return this.URL;}

    public void setURL(String URL){this.URL = URL;}
}
