package xyz.lib.bookstore.convertor.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.dto.UserDTO;
import xyz.lib.bookstore.enums.RoleEnum;
import xyz.lib.bookstore.exception.RoleAllocationException;
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
 * TIME      : 21:26
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class UserDOConverter implements Converter<User, UserDTO> {
    private Long id;

    @Value("User not assigned a role")
    private String roleAllocationExceptionMsg;

    private String name, surname, email, username, password;
    private Boolean isEnabled = true;

    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    /**
     * Convert the source object of type {@code User} to target type {@code UserDTO}.
     *
     * @param user the source object to convert, which must be an instance of {@code User} (never {@code null})
     * @return the converted object, which must be an instance of {@code UserDTO} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public UserDTO convert(final User user) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setRoles(establishEnumRoles(user.getUserRoles()));

        return userDTO;
    }

    private EnumSet<RoleEnum> establishEnumRoles(final Set<UserRole> userRoles) {
        final Set<RoleEnum> roleEnumsSet = new HashSet<>();

        if (userRoles == null || userRoles.isEmpty()) {
            throw new RoleAllocationException(roleAllocationExceptionMsg);
        }

        userRoles.forEach(userRole -> roleEnumsSet.add(RoleEnum.valueOf(userRole.getRole().getName())));
        return EnumSet.copyOf(roleEnumsSet);
    }
}
