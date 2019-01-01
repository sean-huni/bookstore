package xyz.lib.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.exception
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 20:06
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "File not found.")
public class StorageFileNotFoundException extends StorageException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public StorageFileNotFoundException(String message) {
        super(message);
    }
}
