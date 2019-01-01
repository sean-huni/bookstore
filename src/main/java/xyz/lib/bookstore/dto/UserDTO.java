package xyz.lib.bookstore.dto;

import org.springframework.stereotype.Component;
import xyz.lib.bookstore.enums.RoleEnum;

import javax.validation.constraints.Email;
import java.util.EnumSet;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 20:00
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class UserDTO extends AbstractDTO {

    private String name;
    private String surname;
    @Email(message = "{message.badEmail}")
    private String email;
    private String username;
    private String password;
    private Boolean isEnabled;
    private EnumSet<RoleEnum> roles;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public EnumSet<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(EnumSet<RoleEnum> roles) {
        this.roles = roles;
    }
}
