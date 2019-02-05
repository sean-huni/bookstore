package xyz.lib.bookstore.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.model.User;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 04-Mon-Feb-2019
 * TIME      : 00:49
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
 final class UserDetailsConverter implements Converter<User, UserDetails> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param user the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public final UserDetails convert(final User user) {
          return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, false, true, user.getAuthorities());
    }
}
