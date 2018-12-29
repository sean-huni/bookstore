package xyz.lib.bookstore.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.utility
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:28
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class SecurityUtility {
    private static final String SALT = "Psdjfgjgfr74ryjr7214dfhfdFIJJfgj547654FDOdfgDN45464384DNEIdfgNFOFNI";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        String saltStr = salt.toString();
        return saltStr;
    }
}
