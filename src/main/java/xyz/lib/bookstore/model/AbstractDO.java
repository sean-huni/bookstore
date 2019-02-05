package xyz.lib.bookstore.model;

import org.springframework.data.annotation.Id;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.persistence
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 20:22
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public abstract class AbstractDO {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
