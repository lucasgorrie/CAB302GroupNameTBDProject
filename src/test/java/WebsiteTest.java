import com.example.cab302groupnametbdproject.model.associatedWebsites.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebsiteTest {

    private Website website;

    // Setup test object
    @BeforeEach
    public void setUp() {
        website = new Website("Bing.com");
    }

    // Test GetSet methods for object ID
    @Test
    public void testGetSetId() {
        website.setId(9);
        assertEquals(9, website.getId());
    }

    // Test GetSet methods for URL
    @Test
    public void testGetSetURL(){
        website.setURL("google.com");
        assertEquals("google.com", website.getURL());

    }
}
