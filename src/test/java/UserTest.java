import com.example.cab302groupnametbdproject.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp(){
        user = new User("PARENT",null, "testUsername", "testFirstName", "testLastName", "testEmail@email.com", "testPass1!");
    }

    @Test
    public void testGetSetId(){
        user.setId(4);
        assertEquals(4, user.getId());
    }

    @Test
    public void testGetSetUserType(){
        user.setUserType("CHILD");
        assertEquals("CHILD", user.getUserType());
    }

    @Test
    public void testGetSetParentId(){
        user.setParentId(4);
        assertEquals(4, user.getParentId());
    }

    @Test
    public void testGetSetUsername(){
        user.setUsername("changedUsername");
        assertEquals("changedUsername", user.getUsername());
    }

    @Test
    public void testGetSetFirstName(){
        user.setFirstName("changedFirstName");
        assertEquals("changedFirstName", user.getFirstName());
    }

    @Test
    public void testGetSetLastName(){
        user.setLastName("changedLastName");
        assertEquals("changedLastName", user.getLastName());
    }

    @Test
    public void testGetSetEmail(){
        user.setEmail("changedEmail@change.com");
        assertEquals("changedEmail@change.com", user.getEmail());
    }

    @Test
    public void testGetFullName(){
        user.setFirstName("First");
        user.setLastName("Last");
        assertEquals("First Last", user.getFullName());
    }

    @Test
    public void testGetSetPassword(){
        user.setPassword("changedPass1!");
        assertEquals("changedPass1!", user.getPassword());
    }

    @Test
    public void testUserPasswordCorrect(){
        user.setPassword("testPass");
        assertTrue(user.UserPasswordCorrect("testPass"));
    }

    // ADD TESTS HERE WHEN ENCRYPTION/DECRYPTION FUNCTIONS ARE FINALISED

}
