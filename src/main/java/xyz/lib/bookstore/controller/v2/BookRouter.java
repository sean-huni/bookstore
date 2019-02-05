package xyz.lib.bookstore.controller.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import xyz.lib.bookstore.service.BookService;
import xyz.lib.bookstore.service.StorageService;

import javax.annotation.Resource;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller.v2
 * USER      : sean
 * DATE      : 04-Mon-Feb-2019
 * TIME      : 02:09
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Slf4j
@Configuration
public class BookRouter {
    private BookService bookService;

    @Resource
    private StorageService storageService;

    @Autowired
    public BookRouter(BookService bookService, StorageService storageService) {
        this.bookService = bookService;
        this.storageService = storageService;
    }

//    @Bean
//    public RouterFunction<ServerResponse> saveNewBook() {
//        return;
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> bookRouter() {
//        return
//                route(POST("/books"), req -> req.bodyToMono(BookDTO.class)
//                        .doOnNext(bookService::saveNewBook)
//                        .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, BOOK_COULD_NOT_BE_SAVED + "\nReason: " + e.getMessage())))
//                        .then(ok().build()))
//
//                        .and(route(GET("/books"), req -> ok().body(
//                                req.queryParam("all-by-title")
//                                        .map(s -> bookService.findAllBooksByTitleContaining(s))
//                                        .orElse(bookService.findAllBooks())
//                                , BookDTO.class)))
//
//                        .and(route(GET("/books/{id}"),
//                                req -> ok().body(bookService.findBookById(Long.valueOf(req.pathVariable("id"))), BookDTO.class)));
//    }
}
