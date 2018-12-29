package xyz.lib.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.lib.bookstore.domain.Role;
import xyz.lib.bookstore.domain.User;
import xyz.lib.bookstore.domain.UserRole;
import xyz.lib.bookstore.service.UserService;
import xyz.lib.bookstore.utility.SecurityUtility;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication {
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @PostConstruct
    private void setupAppData() {
        userdata();
    }

    private void userdata() {
        final UserRole userRole = new UserRole();
        final Role role = new Role();
        final CharSequence charSequence = "password1";
        final Set<UserRole> userRoles = new HashSet<>();


        role.setName("ROLE_ADMIN");
        userRole.setRole(role);

        final User user = new User("Sean", "Huni", "admin@bookstore.xyz", "sean2kay",
                SecurityUtility.passwordEncoder().encode(charSequence), true);

        userRole.setUser(user);
        userRoles.add(userRole);

        userService.insertNewUser(user, userRoles);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}

