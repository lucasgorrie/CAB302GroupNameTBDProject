package com.example.cab302groupnametbdproject.model.associatedWebsites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebsiteTest {
    private Website website;
    @BeforeEach
    public void setUp() {
        website = new Website("Bing.com");
    }
    @Test
    public void testGetSetId() {
        website.setId(9);
        assertEquals(9, website.getId());
    }

    @Test
    public void testGetSetURL(){
        website.setURL("google.com");
        assertEquals("google.com", website.getURL());
    }

}