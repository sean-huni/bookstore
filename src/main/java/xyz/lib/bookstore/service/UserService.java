package xyz.lib.bookstore.service;

import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.model.UserRole;
import xyz.lib.bookstore.exception.UserAlreadyExistException;

import java.util.Set;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 00:41
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface UserService {

    /**
     * Save of Update existing/new user to the database.
     *
     * @param user      {@link User} to be saved.
     * @param userRoles {@link UserRole} which the user belongs to.
     * @return saved User.
     * @throws UserAlreadyExistException if the user already exists in the DB.
     */
    User insertNewUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistException;
}
