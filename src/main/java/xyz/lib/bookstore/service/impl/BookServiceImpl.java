package xyz.lib.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lib.bookstore.convertor.book.BookDOConverter;
import xyz.lib.bookstore.convertor.book.BookDTOConverter;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.exception.ResourceNotFound;
import xyz.lib.bookstore.model.Book;
import xyz.lib.bookstore.repo.BookRepo;
import xyz.lib.bookstore.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public BookDTO findBookById(Long id) throws ResourceNotFound {
        Optional<Book> optionalBook = bookRepo.findById(id);

        return bookDTOConverter.convert(optionalBook.orElseThrow(() -> new ResourceNotFound("Book associated with id '" + id + "' not found.")));
    }

    /**
     * Find all {@link BookDTO}.
     *
     * @return A {@link Collection <BookDTO>} of {@link Book}.
     */
    @Override
    public Collection<BookDTO> findAllBooks() {
        List<Book> bookList = bookRepo.findAll();

        return bookList.stream().map(book -> bookDTOConverter.convert(book)).collect(Collectors.toList());
    }

    /**
     * Saves a new book.
     *
     * @param newBook new {@link BookDTO} to save.
     * @return saved book.
     */
    @Override
    public BookDTO saveNewBook(BookDTO newBook) throws BookConstraintViolationException {
        // throws Caused by: java.lang.IllegalArgumentException: The given id must not be null!
//        if (bookRepo.findById(newBook.getId()).isPresent()) {
//            throw new BookConstraintViolationException("Cannot save a new book. A Book with id: " + newBook.getId() + " already exists.");
//        }

        newBook.setId(null);

        Book book = bookDOConverter.convert(newBook);

        return bookDTOConverter.convert(bookRepo.save(book));
    }

    /**
     * Update an existing BookDTO.
     *
     * @param bookDTO update an existing {@link BookDTO}.
     * @return {@link BookDTO}.
     */
    @Override
    public BookDTO updateBook(BookDTO bookDTO) {

        Book book = bookDOConverter.convert(bookDTO);
        return bookDTOConverter.convert(bookRepo.save(book));
    }

    /**
     * Delete an existing {@link BookDTO}
     *
     * @param bookDTO {@link BookDTO}
     */
    @Override
    public void deleteBook(BookDTO bookDTO) {
        Book book = bookDOConverter.convert(bookDTO);
        bookRepo.delete(book);
    }


    /**
     * Deletes book by id.
     *
     * @param id of the book.
     */
    @Override
    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }
}
