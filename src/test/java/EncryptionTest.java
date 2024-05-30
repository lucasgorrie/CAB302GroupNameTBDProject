import com.example.cab302groupnametbdproject.model.passwords.Encryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// The encryption class will use password input and the user's key (plaintext application password stored in memory
//  on login) to encrypt and decrypt associated passwords
public class EncryptionTest {

    private String testPassword;
    private String testKey;

    // Setup test string
    @BeforeEach
    public void setUp() {
        testPassword = "TestPass1!2@";
        testKey = "TestKey1!2@";
    }

    // Test that the encrypted password is not equal to the plaintext password
    @Test
    public void testEncrypt(){
        String encryptedPassword = Encryption.encrypt(testPassword, testKey);
        assertNotEquals(testPassword, encryptedPassword);
    }

    // Test that the decrypted password is equal to the plaintext password
    @Test
    public void testDecrypt(){
        String encryptedPassword = Encryption.encrypt(testPassword, testKey);
        assertEquals(testPassword, Encryption.decrypt(encryptedPassword, testKey));
    }

}
