package xyz.lib.bookstore.convertor.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.dto.UserDTO;
import xyz.lib.bookstore.enums.RoleEnum;
import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.model.UserRole;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.convertor.user
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 22:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class UserDTOConverter implements Converter<UserDTO, User> {
    /**
     * Convert the source object of type {@code UserDTO} to target type {@code User}.
     *
     * @param userDTO the source object to convert, which must be an instance of {@code UserDTO} (never {@code null})
     * @return the converted object, which must be an instance of {@code User} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public User convert(UserDTO userDTO) {
        return null;
    }


    private Set<UserRole> establishRoles(EnumSet<RoleEnum> roleEnums) {
        Set<UserRole> userRoles = new HashSet<>();

        return userRoles;
    }
}
