package xyz.lib.bookstore.convertor.book;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.model.Book;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.utility
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 18:33
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class BookDOConverter implements Converter<BookDTO, Book> {

    /**
     * Convert the source object of type {@code BookDTO} to target type {@code Book}.
     *
     * @param bookDTO the source object to convert, which must be an instance of {@code BookDTO} (never {@code null})
     * @return the converted object, which must be an instance of {@code Book} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Book convert(final BookDTO bookDTO) {
        final Book book = new Book();

        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setDatePublished(bookDTO.getDatePublished());
        book.setLanguage(bookDTO.getLanguage());
        book.setCategory(bookDTO.getCategory());
        book.setPages(bookDTO.getPages());
        book.setFormat(bookDTO.getFormat());
        book.setIsbn(bookDTO.getIsbn());
        book.setWeight(bookDTO.getWeight());
        book.setListPrice(bookDTO.getListPrice());
        book.setOurPrice(bookDTO.getOurPrice());
        book.setActive(bookDTO.isActive());
        book.setDescription(bookDTO.getDescription());
        book.setQuantity(bookDTO.getQuantity());
//        book.setBookImage(bookDTO.getBookImage());

        return book;
    }
}
