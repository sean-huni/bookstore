package xyz.lib.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import xyz.lib.bookstore.enums.Action;

import java.io.Serializable;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 20-Sun-Jan-2019
 * TIME      : 16:18
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class Message implements Serializable {
    private static final Long serialVersionUID = 3434556687882937L;

    @JsonProperty("from")
    private From from;
    @JsonProperty("content")
    private Object content;
    @JsonProperty("action")
    private Action action;

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", content='" + content + '\'' +
                ", action=" + action +
                '}';
    }
}
