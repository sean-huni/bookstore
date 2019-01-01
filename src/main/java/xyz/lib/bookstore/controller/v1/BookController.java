package xyz.lib.bookstore.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.service.BookService;

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
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
    public Collection<BookDTO> readAllBooks() {
        //Logic to retrieve resource.
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

    @PutMapping("/{id}")
    @ResponseBody
    public BookDTO updateBookById(@PathVariable(name = "id") Long id, @Valid @RequestBody BookDTO bookDTO) throws Exception {
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
