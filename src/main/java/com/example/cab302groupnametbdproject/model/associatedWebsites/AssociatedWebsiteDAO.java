package com.example.cab302groupnametbdproject.model.associatedWebsites;

import java.util.List;


// Interface for Data Access Object and CRUD operations
public interface AssociatedWebsiteDAO {

    /**
     * Adds a new associated website to the database.
     * @param website The user to add.
     */
    public void addWebsite(Website website);

    /**
     * Deletes a website from the database.
     * @param website The user to delete.
     */
    public void deleteWebsite(Website website);

    /**
     * Retrieves a website from the database.
     * @param id The id of the website to retrieve.
     * @return The website with the given id, or null if not found.
     */
    public Website getWebsite(int id);

    /**
     * Retrieves all websites from the database.
     * @return A list of all websites in the database.
     */
    public List<Website> getAllWebsites();

    Website getWebsiteFromURL(String url);
}