package xyz.lib.bookstore.repo;

import xyz.lib.bookstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
