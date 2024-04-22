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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public String getKey(){return key;}

    public void setKey(String key){this.key = key;}

    public void encryptPassword(){
        // FUNCTIONALITY TO BE ADDED
        String encrypted_password = password; // <-- CHANGE THIS LATER
        setPassword(encrypted_password);
    }

    public String decryptedPassword(){
        // FUNCTIONALITY TO BE ADDED
        String decrypted_password = password; // <-- CHANGE THIS LATER
        return decrypted_password;
    }

}