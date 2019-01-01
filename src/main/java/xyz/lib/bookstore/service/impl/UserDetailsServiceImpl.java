package xyz.lib.bookstore.service.impl;

import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.service
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:34
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByUsername(username));
        String errorMsg = "Username: " + username + " not found...";

        if (!optionalUser.isPresent()) {
            LOGGER.warn(errorMsg);
        }

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(errorMsg));
    }
}
