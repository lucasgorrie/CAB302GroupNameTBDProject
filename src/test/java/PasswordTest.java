import com.example.cab302groupnametbdproject.model.passwords.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {

    private Password password;

    @BeforeEach
    public void setUp(){
        password = new Password(1, 2, "Password44$$", "01110001");
    }

    @Test
    public void testGetSetId(){
        password.setId(7);
        assertEquals(7, password.getId());
    }

    @Test
    public void testGetUserId(){
        assertEquals(1, password.getUser_id());
    }

    @Test
    public void testGetWebsiteId(){
        assertEquals(2, password.getWebsite_id());
    }

    @Test
    public void testGetSetPasswordContent(){
        password.setPassword("ChangedPassword1!");
        assertEquals("ChangedPassword1!", password.getPasswordContent());
    }

    @Test
    public void testGetSetKey(){
        password.setKey("00000000");
        assertEquals("00000000", password.getKey());
    }

    // ADD ENCRYPTION/DECRYPTION TESTS WHEN METHODS ARE FINISHED


}
