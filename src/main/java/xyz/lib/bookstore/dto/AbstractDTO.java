package xyz.lib.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.persistence
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 20:22
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public abstract class AbstractDTO implements Serializable {
    private static final Long serialVersionUID = 3455534529382937L;

    @JsonProperty("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
