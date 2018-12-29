package xyz.lib.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.lib.bookstore.domain.User;
import xyz.lib.bookstore.domain.UserRole;
import xyz.lib.bookstore.exception.UserAlreadyExistException;
import xyz.lib.bookstore.repo.UserRepo;
import xyz.lib.bookstore.service.UserService;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 00:42
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Save of Update existing/new user to the database.
     *
     * @param user      {@link User} to be saved.
     * @param userRoles {@link UserRole} which the user belongs to.
     * @return saved User.
     * @throws UserAlreadyExistException if the user already exists in the DB.
     */
    @Override
    @Transactional
    public User insertNewUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistException {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            LOGGER.warn("Username is null/empty...");
            throw new UserAlreadyExistException("Username is empty/null.");
        }

        Optional<User> localUser = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));

        if (!localUser.isPresent()) {
            user.getUserRoles().addAll(userRoles);
            localUser = Optional.ofNullable(userRepo.save(user));
        } else {
            LOGGER.warn("Username {} already exists...", Objects.requireNonNull(localUser.orElse(null)).getUsername());
            throw new UserAlreadyExistException("Username: " + user.getUsername() + " already exist.");
        }

        return localUser.get();
    }
}
