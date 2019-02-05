package xyz.lib.bookstore.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.StorageService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

import static xyz.lib.bookstore.constants.Constants.PATH_VARIABLE_ID_IS_EXPECTED;
import static xyz.lib.bookstore.constants.Constants.RESP_JSON_FORMAT;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 14:35
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Slf4j
@RestController
@RequestMapping("/v1/books")
public class BookController {
    private static final String MSG_BOOK_DELETED_SUCCESSFUL = "Book Deleted Successfully!!!";
    private static final String MSG_BOOK_UPLOADED_SUCCESSFUL = "\"Image Uploaded Successfully!!!\"";
    private BookService bookService;

    @Resource
    private StorageService storageService;

    @Autowired
    public BookController(BookService bookService, StorageService storageService) {
        this.bookService = bookService;
        this.storageService = storageService;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDTO> createBook(@Valid @RequestBody BookDTO book) {
        //Logic to save a new book resource.
        return bookService.saveNewBook(book);
    }

    /**
     * Uploads the image to the server.
     * Precondition: the id of the {@link BookDTO} must exist in the database before saving the new image.
     *
     * @param id        of the {@link BookDTO}
     * @param multipart {@link MultipartFile}
     * @return {@link HttpStatus} to indicate success/failure.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping(value = "/{id}/images")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createBookImg(@PathVariable("id") Long id, @RequestParam("multipart") MultipartFile multipart) {
        final BookDTO bookDTO = bookService.findBookById(id).block();
        Optional<String> filePath = null;

        try {
            filePath = storageService.uploadFile(id, multipart).blockOptional();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }

        bookDTO.setImgPath(filePath.get());
        bookService.updateBook(bookDTO);
        String resp = String.format(RESP_JSON_FORMAT, MSG_BOOK_UPLOADED_SUCCESSFUL);

        return Mono.just(resp);
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    @ResponseBody
    public Flux<BookDTO> readAllBooks(@RequestParam(value = "all-by-title", required = false) String title) {
        log.info("Title Search: " + title);
        //Call to service to retrieve resource.
        if (title != null && !title.trim().isEmpty()) {
            return bookService.findAllBooksByTitleContaining(title);
        }
        return bookService.findAllBooks();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    @ResponseBody
    public Mono<BookDTO> readBookById(@PathVariable(name = "id") Long id) {
        //Call to service to retrieve resource.
        Optional<Long> optionalId = Optional.ofNullable(id);

        if (!optionalId.isPresent() || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PATH_VARIABLE_ID_IS_EXPECTED);
        }

        try {
            return bookService.findBookById(id);
        } catch (ResourceNotFound rnf) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, rnf.getMessage(), rnf);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseBody @ResponseStatus(HttpStatus.OK)
    public Mono<BookDTO> updateBookById(@PathVariable(name = "id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        //Logic to update resource.
        Optional<Long> optionalId = Optional.ofNullable(id);

        if (!optionalId.isPresent() || id == 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PATH_VARIABLE_ID_IS_EXPECTED);
        }

        bookDTO.setId(id);
        return bookService.updateBook(bookDTO);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<String>> deleteBookById(@PathVariable(name = "id") Long id) {
        //Logic to delete resource.
        Optional<Long> optionalId = Optional.ofNullable(id);

        if (!optionalId.isPresent() || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PATH_VARIABLE_ID_IS_EXPECTED);
        }

        try {
            storageService.deleteFile(id);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }

        bookService.deleteById(id);

        String resp = String.format(RESP_JSON_FORMAT, MSG_BOOK_DELETED_SUCCESSFUL);
        return Mono.just(new ResponseEntity<>(resp, HttpStatus.OK));
    }

}
