package xyz.lib.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.dto
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 20:26
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class User extends AbstractDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar")
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
