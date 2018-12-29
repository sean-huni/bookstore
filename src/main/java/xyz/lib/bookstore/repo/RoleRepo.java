package xyz.lib.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.lib.bookstore.domain.Role;

import java.util.Collection;

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
public interface RoleRepo extends JpaRepository<Role, Long> {
    Collection<Role> findAllByName(String name);

}
