package xyz.lib.bookstore.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.model.User;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.repo
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:52
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Repository
public interface UserRepo extends ReactiveMongoRepository<User, Long> {
    Mono<User> findByUsername(String username);
    Mono<User> findByEmail(String email);
}
