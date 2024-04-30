package com.example.cab302groupnametbdproject.model.users;

import com.example.cab302groupnametbdproject.model.passwords.Password;

// Structure of User objects
public class User {
    private int id;
    private String user_type; // <-- PARENT or CHILD
    private int parent_id = 0; // <-- PK of PARENT User, references a User in this table's PK. is a FK.
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String key = null;

    // Constructor
    public User(String user_type, String username, String firstName, String lastName, String email, String password) {
        this.user_type = user_type;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @return the id of the User
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id to be set for the User
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the userType of the User
     */
    public String getUserType() {
        return user_type;
    }

    /**
     *
     * @param type the type to be set for the User
     */
    public void setUserType(String type) {
        this.user_type = type;
    }

    /**
     *
     * @return the id of the Parent User
     */
    public int getParentId() {
        return parent_id;
    }

    /**
     *
     * @param parent_id the Parent id to be set for the User
     */
    public void setParentId(Integer parent_id) {
        this.parent_id = parent_id;
    }

    /**
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username the username to be set for the User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return the first name of the User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName the name to be set for the User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the last name of the User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName the last name to be set for the User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the email of the User
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email the email to be set for the User
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the first name and the last name of the User
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     *
     * @return the application password of the User
     */
    public String getPassword(){
        return password;
    }

    /**
     *
     * @param password the password to be set for the User
     */
    public void setPassword(String password){this.password = password;}

    /**
     *
     * @return the encryption/decryption key for the Password
     */
    public String getKey(){return key;}

    /**
     *
     * @param key the encryption/decryption key to be set for the Password
     */
    public void setKey(String key){this.key = key;}


    /**
     * Encrypts the application password of the User
     */
    public void encryptUserPassword(){
        // ADD FUNCTIONALITY TO ENCRYPT USER APPLICATION PASSWORD
    }

    /**
     * Decrypts the application password of the User
     */
    public void decryptUserPassword(){
        // ADD FUNCTIONALITY TO DECRYPT USER APPLICATION PASSWORD
    }

    /**
     *
     * @param passwordEntered the password entered by the user into the login form
     * @return whether the input matches the User's application password
     * May need to be updated to call the @decryptUserPassword function later down the line
     */
    public boolean UserPasswordCorrect(String passwordEntered){
        return this.password == passwordEntered;
    }

}