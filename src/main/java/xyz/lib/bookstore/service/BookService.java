package xyz.lib.bookstore.service;

import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.exception.ResourceNotFound;

import java.util.Collection;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 15:31
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public interface BookService {

    /**
     * Find a book by its ID.
     *
     * @param id of the {@link BookDTO}
     * @return {@link BookDTO} found.
     */
    BookDTO findBookById(Long id) throws ResourceNotFound;

    /**
     * Finds all books that match the keyword criteria.
     *
     * @param keyword criteria for searching the book-titles.
     * @return a {@link Collection<BookDTO>} that match the the criteria.
     */
    Collection<BookDTO> findAllBooksByTitleContaining(String keyword);

    /**
     * Find all {@link BookDTO}.
     *
     * @return A {@link Collection<BookDTO>} of {@link BookDTO}.
     */
    Collection<BookDTO> findAllBooks();

    /**
     * Saves a new book.
     *
     * @param newBook new {@link BookDTO} to save.
     * @return saved {@link BookDTO}.
     */
    BookDTO saveNewBook(BookDTO newBook) throws BookConstraintViolationException;

    /**
     * Update an existing BookDTO.
     *
     * @param bookDTO update an existing {@link BookDTO}.
     * @return {@link BookDTO}.
     */
    BookDTO updateBook(BookDTO bookDTO);

    /**
     * Delete an existing {@link BookDTO}
     *
     * @param book {@link BookDTO}
     */
    void deleteBook(BookDTO book);

    /**
     * Deletes book by id.
     *
     * @param id of the book.
     */
    void deleteById(Long id);
}
