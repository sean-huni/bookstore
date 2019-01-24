package xyz.lib.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.exception
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 01:07
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Integrity Violation. Username already Exists.")
public class UserAlreadyExistException extends AuthenticationException {
    private static final long serialVersionUID = -3389416993124228948L;

    /**
     * User Already Exist Exception
     *
     * @param msg {@link String} Error From.
     */
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
