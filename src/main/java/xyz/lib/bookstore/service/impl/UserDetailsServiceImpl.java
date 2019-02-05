package xyz.lib.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.repo.UserRepo;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.service
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:34
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepo userRepo;

    private Converter<User, UserDetails> userDetailsConverter;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setUserDetailsConverter(Converter<User, UserDetails> userDetailsConverter) {
        this.userDetailsConverter = userDetailsConverter;
    }

    /**
     * Find the {@link UserDetails} by username.
     *
     * @param username the username to look up
     * @return the {@link UserDetails}. Cannot be null
     */
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        final String errorMsg = String.format("Username: %s not found...", username);

        return userRepo.findByUsername(username).switchIfEmpty(Mono.defer(
                () -> Mono.error(new UsernameNotFoundException(errorMsg))
        )).map(user -> userDetailsConverter.convert(user));
    }
}
