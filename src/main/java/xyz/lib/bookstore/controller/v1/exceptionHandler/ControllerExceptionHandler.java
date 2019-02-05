package xyz.lib.bookstore.controller.v1.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import xyz.lib.bookstore.exception.BookAlreadyExistException;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.exception.StorageException;

import java.io.IOException;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller.v1
 * USER      : sean
 * DATE      : 29-Tue-Jan-2019
 * TIME      : 00:59
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Integrity Violation. Book already Exists.")
    @ExceptionHandler({BookAlreadyExistException.class, BookConstraintViolationException.class})
    public ResponseStatusException handleBookAlreadyExistException(Exception e) {
        log.error("Resource already exist...");
        log.error(e.getMessage(), e);

        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Image upload error. Failed to upload image.")
    @ExceptionHandler({ResourceNotFound.class, IOException.class})
    public ResponseStatusException handleImageUploadException(Exception e) {

        log.error("Failed to upload image...");
        log.error(e.getMessage(), e);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to upload image...");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Failed to store image.")
    @ExceptionHandler({StorageException.class})
    public ResponseStatusException handleStorageException(Exception e) {

        log.error("Failed to upload image...");
        log.error(e.getMessage(), e);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to store image...");
    }
}
