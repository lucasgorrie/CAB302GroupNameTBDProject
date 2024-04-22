package com.example.cab302groupnametbdproject.model.passwords;

public interface PasswordDAO {

    /**
     * Adds a new password to the database.
     * @param password The password to add.
     */
    public void addPassword(Password password);

    /**
     * Updates a password in the database.
     * @param password The password to update.
     */
    public void updatePassword(Password password);

    /**
     * Deletes a password from the database.
     * @param password The password to delete.
     */
    public void deletePassword(Password password);

    /**
     * Retrieves a password from the database.
     * @param id The id of the password to retrieve.
     * @return The password with the given id, or null if not found.
     */
    public Password getPassword(int id);

}
