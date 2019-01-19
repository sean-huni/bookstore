package xyz.lib.bookstore.controller.v1;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import xyz.lib.bookstore.dto.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller.v1
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 20:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@Controller
public class ChatController {

    @CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(final Message message) throws Exception {
        final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return message;
    }
}
