package xyz.lib.bookstore.model;

import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.persistence
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 13:26
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Entity
@Table(name = "user_role")
public class UserRole extends AbstractDO implements Serializable {
    @Getter(AccessLevel.NONE)
    private static final Long serialVersionUID = 234894724873L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
