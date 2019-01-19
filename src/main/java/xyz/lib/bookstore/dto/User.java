package xyz.lib.bookstore.dto;

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
    private String name;
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
}
