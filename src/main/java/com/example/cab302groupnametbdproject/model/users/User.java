package com.example.cab302groupnametbdproject.model.users;

// Structure of User objects
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Constructor
    public User(String firstName, String lastName, String email, String password) {
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