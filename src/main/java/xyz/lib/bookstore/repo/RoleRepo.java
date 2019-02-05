package xyz.lib.bookstore.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import xyz.lib.bookstore.model.Role;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.repo
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 00:49
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Repository
public interface RoleRepo extends ReactiveMongoRepository<Role, Long> {
    Flux<Role> findAllByName(String name);

}
