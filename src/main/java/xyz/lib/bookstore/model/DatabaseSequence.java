package xyz.lib.bookstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.model
 * USER      : sean
 * DATE      : 05-Tue-Feb-2019
 * TIME      : 19:29
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Document(collection = "db_sequence")
public class DatabaseSequence {

    @Id
    private String id;

    private Long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
