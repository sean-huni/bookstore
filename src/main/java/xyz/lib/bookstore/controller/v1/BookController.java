package xyz.lib.bookstore.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.StorageService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

import static xyz.lib.bookstore.constants.Constants.PATH_VARIABLE_ID_IS_EXPECTED;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 14:35
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@RestController
@RequestMapping("/v1/books")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;
    private StorageService storageService;

    @Autowired
    public BookController(BookService bookService, StorageService storageService) {
        this.bookService = bookService;
        this.storageService = storageService;
    }

    @PostMapping
    @ResponseBody
    public BookDTO createBook(@Valid @RequestBody BookDTO book) {
        //Logic to save a new book resource.
        try {
            return bookService.saveNewBook(book);
        } catch (BookConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }

    @GetMapping
    @ResponseBody
    public Collection<BookDTO> readAllBooks(@RequestParam(value = "all-by-title", required = false) String title) {
        //Logic to retrieve resource.
        if (title != null && !title.trim().isEmpty()) {
            return bookService.findAllBooksByTitleContaining(title);
        }
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookDTO readBookById(@PathVariable(name = "id") Long id) {
        //Logic to retrieve resource.
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


    /**
     * Uploads the image to the server.
     * Precondition: the id of the {@link BookDTO} must exist in the database before saving the new image.
     *
     * @param id        of the {@link BookDTO}
     * @param multipart {@link MultipartFile}
     * @return {@link HttpStatus} to indicate success/failure.
     */
    @PostMapping(value = "/{id}/images")
    @ResponseBody
    public ResponseEntity<String> createBookImg(@PathVariable("id") Long id, @RequestParam("multipart") MultipartFile multipart) {
        try {
            storageService.uploadFile(id, multipart);
            return new ResponseEntity<>("Upload Successful!", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public BookDTO updateBookById(@PathVariable(name = "id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        //Logic to update resource.
        Optional<Long> optionalId = Optional.ofNullable(id);

        if (!optionalId.isPresent() || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PATH_VARIABLE_ID_IS_EXPECTED);
        }

        bookDTO.setId(id);
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteBookById(@PathVariable(name = "id") Long id) {
        //Logic to delete resource.
        Optional<Long> optionalId = Optional.ofNullable(id);

        if (!optionalId.isPresent() || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, PATH_VARIABLE_ID_IS_EXPECTED);
        }

        bookService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
