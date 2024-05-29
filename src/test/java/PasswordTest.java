import com.example.cab302groupnametbdproject.model.passwords.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {

    private Password password;

    // Setup test object
    @BeforeEach
    public void setUp(){
        password = new Password(1, 2, "Password44$$");
    }

    // Test GetSet methods for object ID
    @Test
    public void testGetSetId(){
        password.setId(7);
        assertEquals(7, password.getId());
    }

    // Test GetSet methods for object user ID
    @Test
    public void testGetUserId(){
        assertEquals(1, password.getUser_id());
    }

    // Test GetSet methods for website ID
    @Test
    public void testGetWebsiteId(){
        assertEquals(2, password.getWebsite_id());
    }

    // Test GetSet methods for object password content
    @Test
    public void testGetSetPasswordContent(){
        password.setPassword("ChangedPassword1!");
        assertEquals("ChangedPassword1!", password.getPasswordContent());

    }
}
