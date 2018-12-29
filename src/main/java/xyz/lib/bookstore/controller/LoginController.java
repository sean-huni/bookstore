package xyz.lib.bookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.lib.bookstore.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 13:23
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/token")
    @ResponseBody
    public ResponseEntity token(HttpSession session, HttpServletRequest request) {

        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        Integer remotePort = request.getRemotePort();


        LOGGER.info("Remote Host: {}", remoteHost);
        LOGGER.info("Remote Post: {}", remotePort);
        LOGGER.info("Remote Addr: {}", remoteAddr);
        return new ResponseEntity(Collections.singletonMap("token", session.getId()), HttpStatus.OK);
    }

    @GetMapping("/checkSession")
    @ResponseBody
    public ResponseEntity checkSession() {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }

    @DeleteMapping("/login")
    @ResponseBody
    public ResponseEntity logout(@RequestParam(value = "logout", required = false) Boolean logout){
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully!", HttpStatus.ACCEPTED);
    }
}
