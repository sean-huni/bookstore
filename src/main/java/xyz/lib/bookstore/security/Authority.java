package xyz.lib.bookstore.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.security
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 13:20
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public class Authority implements GrantedAuthority, Serializable {
    private static final Long serialVersionUID = 12893723565634L;

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
