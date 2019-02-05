package xyz.lib.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.convertor.book.BookDOConverter;
import xyz.lib.bookstore.convertor.book.BookDTOConverter;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookAlreadyExistException;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.model.Book;
import xyz.lib.bookstore.repo.BookRepo;
import xyz.lib.bookstore.service.BookService;

import java.util.Collection;
import java.util.Optional;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 16:29
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;
    private BookDOConverter bookDOConverter;
    private BookDTOConverter bookDTOConverter;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo, BookDOConverter bookDOConverter, BookDTOConverter bookDTOConverter) {
        this.bookRepo = bookRepo;
        this.bookDOConverter = bookDOConverter;
        this.bookDTOConverter = bookDTOConverter;
    }

    /**
     * Find a book by its ID.
     *
     * @param id of the {@link Book}
     * @return {@link Book} found.
     */
    @Override
    public Mono<BookDTO> findBookById(final Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id).blockOptional();

        if (optionalBook.isPresent()) {
           return optionalBook.map(book -> Mono.just(bookDTOConverter.convert(book))).get();
        }
        return Mono.error(new ResourceNotFound(String.format("Book with id: %d not found.", id)));
    }

    /**
     * Finds all books that match the keyword criteria.
     *
     * @param keyword criteria for searching the book-titles.
     * @return a {@link Collection<BookDTO>} that match the the criteria.
     */
    @Override
    public Flux<BookDTO> findAllBooksByTitleContaining(final String keyword) {
        return bookRepo.findAllByTitleContaining(keyword)
                .filter(Book::isActive)
                .map(bookDTOConverter::convert);
    }

    /**
     * Find all {@link BookDTO}.
     *
     * @return A {@link Collection <BookDTO>} of {@link Book}.
     */
    @Override
    public Flux<BookDTO> findAllBooks() {
        Flux<Book> bookList = bookRepo.findAll();
        return bookList.filter(Book::isActive).map(bookDTOConverter::convert);
    }

    /**
     * Saves a new book.
     *
     * @param newBook new {@link BookDTO} to save.
     * @return saved book.
     */
    @Override
    public Mono<BookDTO> saveNewBook(final BookDTO newBook) {
        if (findBookById(newBook.getId()).blockOptional().isEmpty()) {
            return bookRepo.save(bookDOConverter.convert(newBook)).map(bookDTOConverter::convert);
        }
        return Mono.error(new BookAlreadyExistException("Book already exist. You may be trying to overwrite an existing book with the same ID."));
    }

    /**
     * Update an existing BookDTO.
     *
     * @param bookDTO update an existing {@link BookDTO}.
     * @return {@link BookDTO}.
     */
    @Override
    public Mono<BookDTO> updateBook(final BookDTO bookDTO) {
        final Book book = bookDOConverter.convert(bookDTO);
        return bookRepo.save(book).map(bookDTOConverter::convert);
    }

    /**
     * Delete an existing {@link BookDTO}
     *
     * @param bookDTO {@link BookDTO}
     */
    @Override
    public Mono<Void> deleteBook(final BookDTO bookDTO) {
        final Book book = bookDOConverter.convert(bookDTO);
        bookRepo.delete(book).block();
        return Mono.empty();
    }


    /**
     * Deletes book by id.
     *
     * @param id of the book.
     */
    @Override
    public Mono<Void> deleteById(final Long id) {
        bookRepo.deleteById(id).block();
        return Mono.empty();
    }
}
