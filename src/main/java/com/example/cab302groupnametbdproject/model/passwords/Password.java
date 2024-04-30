package com.example.cab302groupnametbdproject.model.passwords;

// Structure of Password objects
public class Password {
    private int id;
    private int user_id; // represents a FK for associated User
    private int website_id; // represents a FK for associated Website
    private String password;

    // Constructor
    public Password(int user_id, int website_id, String password) {
        this.user_id = user_id;
        this.website_id = website_id;
        this.password=password;
    }

    /**
     *
     * @return the id of the Password
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id to be set for the password
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the id of the associated User
     */
    public int getUser_id(){
        return user_id;
    }

    /**
     *
     * @return the id of the associated Website
     */
    public int getWebsite_id() {
        return website_id;
    }

    /**
     *
     * @return the content of the Password
     */
    public String getPasswordContent(){return password;}

    /**
     *
     * @param password the password content to set for the Password object
     */
    public void setPassword(String password){this.password = password;}


    /**
     * Encrypts the password
     */
    public void encryptPassword(){
        // FUNCTIONALITY TO BE ADDED
        String encrypted_password = password; // <-- CHANGE THIS LATER
        setPassword(encrypted_password);
    }

    /**
     *
     * @return the decrypted password
     */
    public String decryptedPassword(){
        // FUNCTIONALITY TO BE ADDED
        String decrypted_password = password; // <-- CHANGE THIS LATER
        return decrypted_password;
    }

}