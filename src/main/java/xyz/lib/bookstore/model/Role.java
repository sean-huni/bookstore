package xyz.lib.bookstore.model;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.OneToMany;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.persistence
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 13:24
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Document("role")
public class Role extends AbstractDO implements Serializable {
    @Transient
    private static final Long serialVersionUID = 3454225534529382937L;

    @Transient
    public static final String SEQUENCE_NAME = "role_sequence";

    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
