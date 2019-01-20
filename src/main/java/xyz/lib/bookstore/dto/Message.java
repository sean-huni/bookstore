package xyz.lib.bookstore.dto;

import xyz.lib.bookstore.enums.Action;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 20:25
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class Message {
    private User user;
    private String message;
    private Action action;

    public Message(User user, String message, Action action) {
        this.user = user;
        this.message = message;
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
