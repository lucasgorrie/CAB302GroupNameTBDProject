package com.example.cab302groupnametbdproject.model.passwords;

// Structure of Password objects
public class Password {
    private int id;
    private String password;
    private String key;

    // Constructor
    public Password(String password, String key) {
        this.password=password;
        this.key=key;
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
     * @return the content of the Password
     */
    public String getPasswordContent(){return password;}

    /**
     *
     * @param password the password content to set for the Password object
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