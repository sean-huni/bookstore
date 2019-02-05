package xyz.lib.bookstore.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.exception.StorageFileNotFoundException;

import java.io.IOException;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 18:40
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface StorageService {

    /**
     * Uploads Image files to the server.
     * In-turn, the image path is saved to the DB with the {@link xyz.lib.bookstore.model.Book}'s ID.
     *
     * @param id        {@link xyz.lib.bookstore.model.Book}'s ID.
     * @param multipart {@link MultipartFile} to be uploaded.
     * @throws ResourceNotFound when {@link xyz.lib.bookstore.model.Book}-ID is not found.
     * @throws IOException      caught if it fails to save the uploaded file-image.
     */
    Mono<String> uploadFile(Long id, MultipartFile multipart) throws ResourceNotFound, IOException;


    /**
     * Deletes both the file-path from the {@link xyz.lib.bookstore.model.Book} DB & directory.
     *
     * @param id {@link xyz.lib.bookstore.model.Book}
     * @throws StorageFileNotFoundException when the file no longer exists on the server.
     */
    Mono<Void> deleteFile(Long id) throws IOException;
}
