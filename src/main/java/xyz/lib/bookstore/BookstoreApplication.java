package xyz.lib.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;
import xyz.lib.bookstore.config.EncryptionConfig;
import xyz.lib.bookstore.config.StorageProperties;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.model.Role;
import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.model.UserRole;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.UserService;
import xyz.lib.bookstore.utility.AppSchemaData;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

//prefix = "raw", ignoreUnknownFields = false
@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
@EnableWebFlux
@EnableAsync(proxyTargetClass = true)
@EnableReactiveMongoRepositories(basePackages="xyz.lib.bookstore.repo")
public class BookstoreApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookstoreApplication.class);
    private UserService userService;
    private BookService bookService;
    private EncryptionConfig securityUtility;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @PostConstruct
    private void setupAppData() {
        userdata();
        bookData();
    }

    @Autowired
    public void setSecurityUtility(EncryptionConfig securityUtility) {
        this.securityUtility = securityUtility;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    private void userdata() {
        final UserRole userRole = new UserRole();
        final Role role = new Role();
        final CharSequence charSequence = "password1";
        final Set<UserRole> userRoles = new HashSet<>();

        role.setId(1L);
        role.setName("ROLE_ADMIN");
        userRole.setRole(role);

        final User user = new User("Sean", "Huni", "admin@bookstore.xyz", "sean2kay",
                securityUtility.passwordEncoder().encode(charSequence), true);
        user.setId(1L);

        userRole.setUser(user);
        userRoles.add(userRole);

        userService.insertNewUser(user, userRoles);
    }

    private void bookData() {
        new AppSchemaData().demoBooks().forEach(bookDTO -> {
            try {
                bookService.saveNewBook(bookDTO);
            } catch (BookConstraintViolationException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }
}

