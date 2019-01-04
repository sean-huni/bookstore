package xyz.lib.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.lib.bookstore.config.EncryptionConfig;
import xyz.lib.bookstore.config.StorageProperties;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.exception.BookConstraintViolationException;
import xyz.lib.bookstore.model.Role;
import xyz.lib.bookstore.model.User;
import xyz.lib.bookstore.model.UserRole;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.UserService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
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

        role.setName("ROLE_ADMIN");
        userRole.setRole(role);

        final User user = new User("Sean", "Huni", "admin@bookstore.xyz", "sean2kay",
                securityUtility.passwordEncoder().encode(charSequence), true);

        userRole.setUser(user);
        userRoles.add(userRole);

        userService.insertNewUser(user, userRoles);
    }

    private void bookData() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Harry Potter");
        bookDTO.setAuthor("JK Rowling");
        bookDTO.setCategory("Fiction");
        bookDTO.setPublisher("Disney");
        bookDTO.setDescription("Fictional characters based on the success of the Harry Potter Trilogy.");
        bookDTO.setFormat("hardcover");
        bookDTO.setImgPath("/dev_0/proj/web/angular/v7/app/bookstore/src/main/resources/images/4.png");
        bookDTO.setPages(10);
        bookDTO.setIsbn("456-456-546465-5124-4");
        bookDTO.setLanguage("english");
        bookDTO.setDatePublished(new Date());
        bookDTO.setOurPrice(new BigDecimal(120.20));
        bookDTO.setListPrice(new BigDecimal(120.20));
        bookDTO.setWeight(1.232);
        bookDTO.setActive(true);
        bookDTO.setQuantity(5);

        try {
            bookService.saveNewBook(bookDTO);
        } catch (BookConstraintViolationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

