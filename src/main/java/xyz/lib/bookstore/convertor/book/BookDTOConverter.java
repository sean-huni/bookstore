package xyz.lib.bookstore.convertor.book;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.model.Book;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.convertor.book
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 19:14
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class BookDTOConverter implements Converter<Book, BookDTO> {

    /**
     * Convert the source object of type {@code Book} to target type {@code BookDTO}.
     *
     * @param book the source object to convert, which must be an instance of {@code Book} (never {@code null})
     * @return the converted object, which must be an instance of {@code BookDTO} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public BookDTO convert(final Book book) {
        final BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setDatePublished(book.getDatePublished());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setPages(book.getPages());
        bookDTO.setFormat(book.getFormat());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setWeight(book.getWeight());
        bookDTO.setListPrice(book.getListPrice());
        bookDTO.setOurPrice(book.getOurPrice());
        bookDTO.setActive(book.isActive());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setImgPath(book.getImgPath());
//        bookDTO.setBookImage(book.getBookImage());

        return bookDTO;
    }
}
