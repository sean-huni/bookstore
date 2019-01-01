package xyz.lib.bookstore.enums;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.option
 * USER      : sean
 * DATE      : 30-Sun-Dec-2018
 * TIME      : 20:03
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public enum RoleEnum {

    ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER"), ASSISTANT("ROLE_ASSISTANT"), PARTNER("ROLE_PARTNER"), CUSTOMER("ROLE_CUSTOMER");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
