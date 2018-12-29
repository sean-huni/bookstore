package xyz.lib.bookstore.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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

@MappedSuperclass
public abstract class AbstractDO implements Serializable {
    private static final Long serialVersionUID = 3455534529382937L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
