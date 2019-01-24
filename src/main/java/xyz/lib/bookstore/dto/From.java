package xyz.lib.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 20:25
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class From extends AbstractDTO implements Serializable {
    private static final Long serialVersionUID = 34555345456382237L;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar")
    private String avatar;

    @Override
    public String toString() {
        return "From{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
