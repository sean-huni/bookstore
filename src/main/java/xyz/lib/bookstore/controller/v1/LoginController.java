package xyz.lib.bookstore.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static xyz.lib.bookstore.constants.Constants.RESP_JSON_FORMAT;

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
    private static final String SESSION_STATUS_ACTIVE = "Session Active!";
    private static final String SESSION_STATUS_LOGOUT = "Logout Successful";



//    @PostMapping("/token")
//    @ResponseBody
//    public ResponseEntity token(HttpSession session, HttpServletRequest request) {
//
//        String remoteHost = request.getRemoteHost();
//        String remoteAddr = request.getRemoteAddr();
//        Integer remotePort = request.getRemotePort();
//
//
//        LOGGER.info("Remote Host: {}", remoteHost);
//        LOGGER.info("Remote Post: {}", remotePort);
//        LOGGER.info("Remote Addr: {}", remoteAddr);
//        return new ResponseEntity<>(Collections.singletonMap("token", session.getId()), HttpStatus.OK);
//    }

    @GetMapping("/checkSession")
    @ResponseBody
    public ResponseEntity<String> checkSession() {
        final String respMsg = String.format(RESP_JSON_FORMAT, SESSION_STATUS_ACTIVE);
        LOGGER.info(respMsg);
        return new ResponseEntity<>(respMsg, HttpStatus.OK);
    }

    @DeleteMapping("/login")
    @ResponseBody
    public ResponseEntity logout(@RequestParam(value = "logout", required = false) Boolean logout) {
        final String respMsg = String.format(RESP_JSON_FORMAT, SESSION_STATUS_LOGOUT);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(respMsg, HttpStatus.ACCEPTED);
    }
}
