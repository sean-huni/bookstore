package xyz.lib.bookstore.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import xyz.lib.bookstore.model.Book;

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
public interface BookRepo extends ReactiveMongoRepository<Book, Long> {
    Flux<Book> findBookByTitle(String title);

    Flux<Book> findAllByTitleContaining(String keyword);

    Flux<Book> findAllByAuthorContains(String author);
}
