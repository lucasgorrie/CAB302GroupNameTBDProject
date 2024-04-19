package com.example.cab302groupnametbdproject.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setPassword(String password){this.password = password;}

    public boolean UserPasswordCorrect(String passwordEntered){
        return this.password == passwordEntered;
    }
}