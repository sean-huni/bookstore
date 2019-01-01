package xyz.lib.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.lib.bookstore.config.EncryptionConfig;
import xyz.lib.bookstore.model.Role;
import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.model.UserRole;
import xyz.lib.bookstore.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication {
    private UserService userService;
    private EncryptionConfig securityUtility;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @PostConstruct
    private void setupAppData() {
        userdata();
    }

    @Autowired
    public void setSecurityUtility(EncryptionConfig securityUtility) {
        this.securityUtility = securityUtility;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private void userdata() {
        final UserRole userRole = new UserRole();
        final Role role = new Role();
        final CharSequence charSequence = "password1";
        final Set<UserRole> userRoles = new HashSet<>();

        role.setName("ROLE_ADMIN");
        userRole.setRole(role);

        final User user = new User("Sean", "Huni", "admin@bookstore.xyz", "sean2kay",
                securityUtility.passwordEncoder().encode(charSequence), true);

        userRole.setUser(user);
        userRoles.add(userRole);

        userService.insertNewUser(user, userRoles);
    }

}

