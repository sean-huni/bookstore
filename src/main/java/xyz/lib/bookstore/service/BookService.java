package xyz.lib.bookstore.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.dto.BookDTO;

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
    Mono<BookDTO> findBookById(final Long id);

    /**
     * Finds all books that match the keyword criteria.
     *
     * @param keyword criteria for searching the book-titles.
     * @return a {@link Collection<BookDTO>} that match the the criteria.
     */
    Flux<BookDTO> findAllBooksByTitleContaining(final String keyword);

    /**
     * Find all {@link BookDTO}.
     *
     * @return A {@link Collection<BookDTO>} of {@link BookDTO}.
     */
    Flux<BookDTO> findAllBooks();

    /**
     * Saves a new book.
     *
     * @param newBook new {@link BookDTO} to save.
     * @return saved {@link BookDTO}.
     */
    Mono<BookDTO> saveNewBook(final BookDTO newBook);

    /**
     * Update an existing BookDTO.
     *
     * @param bookDTO update an existing {@link BookDTO}.
     * @return {@link BookDTO}.
     */
    Mono<BookDTO> updateBook(final BookDTO bookDTO);

    /**
     * Delete an existing {@link BookDTO}
     *
     * @param book {@link BookDTO}
     */
    Mono<Void> deleteBook(final BookDTO book);

    /**
     * Deletes book by id.
     *
     * @param id of the book.
     */
    Mono<Void> deleteById(final Long id);
}
