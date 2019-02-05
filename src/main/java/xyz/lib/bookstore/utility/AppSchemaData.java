package xyz.lib.bookstore.utility;

import xyz.lib.bookstore.dto.BookDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.utility
 * USER      : sean
 * DATE      : 27-Sun-Jan-2019
 * TIME      : 12:28
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class AppSchemaData {

    public List<BookDTO> demoBooks() {
        final List<BookDTO> books = new ArrayList<>();

        final BookDTO book1 = new BookDTO();
        book1.setId(1L);
        book1.setTitle("Harry Potter");
        book1.setAuthor("JK Rowling");
        book1.setCategory("Fiction");
        book1.setPublisher("Disney");
        book1.setDescription("Fictional characters based on the success of the Harry Potter Trilogy.");
        book1.setFormat("hardcover");
        book1.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/1.png");
        book1.setPages(10);
        book1.setIsbn("456-456-546465-5124-4");
        book1.setLanguage("english");
        book1.setDatePublished(new Date());
        book1.setOurPrice(new BigDecimal(120.20));
        book1.setListPrice(new BigDecimal(120.20));
        book1.setWeight(1.232);
        book1.setActive(true);
        book1.setQuantity(5);
        books.add(book1);

        final BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setTitle("Nirbasan");
        book2.setAuthor("Taslima Nasreen");
        book2.setCategory("Fiction");
        book2.setPublisher("West Bengal, India");
        book2.setDescription("A Bangladeshi-Swedish author and former physician[1] who has been living in exile since 1994");
        book2.setFormat("hardcover");
        book2.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/2.png");
        book2.setPages(140);
        book2.setIsbn("126-036-54-65-5124-4");
        book2.setLanguage("english");
        book2.setDatePublished(new Date());
        book2.setOurPrice(new BigDecimal(30.20));
        book2.setListPrice(new BigDecimal(40.20));
        book2.setWeight(2.232);
        book2.setActive(true);
        book2.setQuantity(2);
        books.add(book2);

        final BookDTO book3 = new BookDTO();
        book3.setId(3L);
        book3.setTitle("Fear of Flying");
        book3.setAuthor("Erica Jong");
        book3.setCategory("Fiction");
        book3.setPublisher("Holt, Rinehart and Winston");
        book3.setDescription("the ground-breaking, uninhibited story of Isadora Wing and her desire to fly free caused a national sensation");
        book3.setFormat("paperback");
        book3.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/3.png");
        book3.setPages(140);
        book3.setIsbn("136-036-54-650-1134-2");
        book3.setLanguage("english");
        book3.setDatePublished(new Date());
        book3.setOurPrice(new BigDecimal(50.20));
        book3.setListPrice(new BigDecimal(60.20));
        book3.setWeight(2.232);
        book3.setActive(true);
        book3.setQuantity(12);
        books.add(book3);

        final BookDTO book4 = new BookDTO();
        book4.setId(4L);
        book4.setTitle("Romeo and Juliet");
        book4.setAuthor("William Shakespeare");
        book4.setCategory("Fiction");
        book4.setPublisher("Shakespeare and Company");
        book4.setDescription("A tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families");
        book4.setFormat("paperback");
        book4.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/4.png");
        book4.setPages(98);
        book4.setIsbn("116-236-150-842-111-92");
        book4.setLanguage("english");
        book4.setDatePublished(new Date());
        book4.setOurPrice(new BigDecimal(90.20));
        book4.setListPrice(new BigDecimal(160.20));
        book4.setWeight(1.232);
        book4.setActive(true);
        book4.setQuantity(12);
        books.add(book4);

        final BookDTO book5 = new BookDTO();
        book5.setId(5L);
        book5.setTitle("Othello");
        book5.setAuthor("William Shakespeare");
        book5.setCategory("Fiction");
        book5.setPublisher("Shakespeare and Company");
        book5.setDescription("Othello begins on a street in Venice, in the midst of an argument between Roderigo, a rich man, and Iago. Roderigo has been paying Iago to help him in his suit to Desdemona.");
        book5.setFormat("paperback");
        book5.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/5.png");
        book5.setPages(97);
        book5.setIsbn("126-066-154-131-121-110");
        book5.setLanguage("english");
        book5.setDatePublished(new Date());
        book5.setOurPrice(new BigDecimal(56.20));
        book5.setListPrice(new BigDecimal(67.20));
        book5.setWeight(1.232);
        book5.setActive(true);
        book5.setQuantity(19);
        books.add(book5);


        final BookDTO book6 = new BookDTO();
        book6.setId(6L);
        book6.setTitle("King Lear");
        book6.setAuthor("William Shakespeare");
        book6.setCategory("Fiction");
        book6.setPublisher("Shakespeare and Company");
        book6.setDescription("A tragedy written by William Shakespeare. It depicts the gradual descent into madness of the title character, after he disposes of his kingdom by giving bequests to two of his three daughters egged on by their continual flattery, bringing tragic consequences for all.");
        book6.setFormat("paperback");
        book6.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/6.png");
        book6.setPages(117);
        book6.setIsbn("121-211-101-161-171-710");
        book6.setLanguage("english");
        book6.setDatePublished(new Date());
        book6.setOurPrice(new BigDecimal(56.20));
        book6.setListPrice(new BigDecimal(67.20));
        book6.setWeight(1.11);
        book6.setActive(true);
        book6.setQuantity(20);
        books.add(book6);

        return books;
    }
}
