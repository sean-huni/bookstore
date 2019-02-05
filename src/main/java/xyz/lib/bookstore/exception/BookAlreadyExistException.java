package xyz.lib.bookstore.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.exception
 * USER      : sean
 * DATE      : 28-Mon-Jan-2019
 * TIME      : 02:17
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Integrity Violation. Book already Exists.")
public class BookAlreadyExistException extends DataIntegrityViolationException {
    private static final long serialVersionUID = -3389416993124228948L;

    /**
     * User Already Exist Exception
     *
     * @param msg {@link String} Error From.
     */
    public BookAlreadyExistException(String msg) {
        super(msg);
    }
}

