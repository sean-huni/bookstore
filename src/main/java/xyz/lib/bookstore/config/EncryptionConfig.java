package xyz.lib.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
@Component
public class EncryptionConfig {
    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String SALT = "Psdjfgjgfr74ryjr7214dffjjdghhjjdgjHEHTHETtjtjetjejerertrhfdFIJJfgj547654FDOdfgDN45464384DNEIdfgNFOFNI";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public String randomPassword() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while(salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }

        return salt.toString();
    }
}
