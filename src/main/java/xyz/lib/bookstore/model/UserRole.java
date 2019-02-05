package xyz.lib.bookstore.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.persistence
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 13:26
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Document(collection = "user_role")
public class UserRole extends AbstractDO {

    @Transient
    public static final String SEQUENCE_NAME = "user_role_sequence";
    @DBRef
    private User user;
    @DBRef
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
