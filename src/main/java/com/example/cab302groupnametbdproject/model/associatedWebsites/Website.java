package com.example.cab302groupnametbdproject.model.associatedWebsites;


/**
 * Website object
 */
public class Website {
    private int id;
    private String URL;

    // Constructor
    public Website(String URL){
        this.URL = URL;
    }

    /**
     *
     * @return the id of the object
     */
    public int getId(){return id;}

    /**
     *
     * @param id the id to set
     */
    public void setId(int id){this.id = id;}

    /**
     *
     * @return the URL of the object
     */
    public String getURL(){return this.URL;}

    /**
     *
     * @param URL the URL to be set
     */
    public void setURL(String URL){this.URL = URL;}

}
