package xyz.lib.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

    @Test
    public void contextLoads() {
    }

}

/*

package xyz.lib.bookstore.utility;

        import org.springframework.context.annotation.Bean;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

        import java.security.SecureRandom;
        import java.util.Random;

*/
/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.utility
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:28
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 *//*

public class SecurityUtility {
    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String SALT = "Psdjfgjgfr74ryjr7214dffjjdghhjjdgjHEHTHETtjtjetjejerertrhfdFIJJfgj547654FDOdfgDN45464384DNEIdfgNFOFNI";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }

        return salt.toString();
    }
}
*/
