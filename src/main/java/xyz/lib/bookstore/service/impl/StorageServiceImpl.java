package xyz.lib.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.config.StorageProperties;
import xyz.lib.bookstore.exception.StorageException;
import xyz.lib.bookstore.exception.StorageFileNotFoundException;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 18:41
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);
    private static final String FOLDER_SEPARATOR = "/";
    private final Path rootLocation;
    private BookService bookService;

    @Autowired
    public StorageServiceImpl(BookService bookService, StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.bookService = bookService;
    }

    /**
     * Uploads Image files to the server.
     * In-turn, the image path is saved to the DB with the {@link xyz.lib.bookstore.model.Book}'s ID.
     *
     * @param id        {@link xyz.lib.bookstore.model.Book}'s ID.
     * @param multipart {@link MultipartFile} to be uploaded.
     * @throws IOException caught if it fails to save the uploaded file-image.
     */
    @Override
    public Mono<String> uploadFile(Long id, MultipartFile multipart) throws IOException {
        String filename = id + ".png";

        if (multipart.isEmpty()) {
            throw new StorageException("Cannot store empty file " + filename);
        }

        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException("Cannot store file with relative path outside current directory " + filename);
        }

        try {
            deleteFile(id);
        } catch (IOException se) {
            LOGGER.warn("\nUser id: {}. Msg: {}", id, se.getMessage());
        }

        try (InputStream inputStream = multipart.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        final String filePath = rootLocation.toAbsolutePath().toString() + FOLDER_SEPARATOR + filename;

        LOGGER.info("File-Upload-Path: {}", filePath);

        return Mono.just(filePath);
    }

    /**
     * Deletes both the file-path from the DB & directory.
     *
     * @param id {@link xyz.lib.bookstore.model.Book}
     * @throws IOException                  caught if it fails to delete the uploaded file-image.
     * @throws StorageFileNotFoundException when the file no longer exists on the server.
     */
    @Override
    public Mono<Void> deleteFile(Long id) throws IOException {
        String filename = id + ".png";
        filename = "images/" + filename;

        Files.delete(load(filename));

        return Mono.empty();
    }

    private Path load(String filename) {
        return rootLocation.resolveSibling(filename);
    }
}
