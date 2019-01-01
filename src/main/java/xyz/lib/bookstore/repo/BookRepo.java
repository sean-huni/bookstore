package xyz.lib.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.lib.bookstore.model.Book;

import java.util.Collection;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.repo
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 15:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Book findBookByTitle(String title);

    Collection<Book> findAllByTitleContaining(String keyword);

    Collection<Book> findAllByAuthorContains(String author);
}
