package xyz.lib.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.exception
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 08:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found.")
public class ResourceNotFound extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ResourceNotFound(String message) {
        super(message);
    }
}
