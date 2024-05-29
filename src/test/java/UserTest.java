import com.example.cab302groupnametbdproject.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    // Setup test object
    @BeforeEach
    public void setUp(){
        user = new User("PARENT","testUsername", "testFirstName", "testLastName", "testEmail@email.com", "testPass1!");
    }

    // Test GetSet methods for object ID
    @Test
    public void testGetSetId(){
        user.setId(4);
        assertEquals(4, user.getId());
    }

    // Test GetSet methods for user type
    @Test
    public void testGetSetUserType(){
        user.setUserType("CHILD");
        assertEquals("CHILD", user.getUserType());
    }

    // Test GetSet methods for parent ID
    @Test
    public void testGetSetParentId(){
        user.setParentId(4);
        assertEquals(4, user.getParentId());
    }

    // Test GetSet methods for username
    @Test
    public void testGetSetUsername(){
        user.setUsername("changedUsername");
        assertEquals("changedUsername", user.getUsername());
    }

    // Test GetSet methods for first name
    @Test
    public void testGetSetFirstName(){
        user.setFirstName("changedFirstName");
        assertEquals("changedFirstName", user.getFirstName());
    }

    // Test GetSet methods for last name
    @Test
    public void testGetSetLastName(){
        user.setLastName("changedLastName");
        assertEquals("changedLastName", user.getLastName());
    }

    // Test GetSet methods for email
    @Test
    public void testGetSetEmail(){
        user.setEmail("changedEmail@change.com");
        assertEquals("changedEmail@change.com", user.getEmail());
    }

    // Test Get method for full name
    @Test
    public void testGetFullName(){
        user.setFirstName("First");
        user.setLastName("Last");
        assertEquals("First Last", user.getFullName());
    }

    // Test GetSet methods for application password
    @Test
    public void testGetSetPassword(){
        user.setPassword("changedPass1!");
        assertEquals("changedPass1!", user.getPassword());
    }

    // Test password correct method
    @Test
    public void testUserPasswordCorrect(){
        user.setPassword("testPass");
        assertTrue(user.UserPasswordCorrect("testPass"));

    }
}
