package xyz.lib.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.exception
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 02:15
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Security Violation. Invalid Credentials.")
public class UserSecurityException extends AuthenticationException {
    private static final long serialVersionUID = -3389416993124228948L;

    /**
     * Constructs a {@code SecurityException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public UserSecurityException(String s) {
        super(s);
    }
}
