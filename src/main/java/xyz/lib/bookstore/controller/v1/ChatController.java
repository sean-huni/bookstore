package xyz.lib.bookstore.controller.v1;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import xyz.lib.bookstore.dto.Message;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller.v1
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 20:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

@Controller
public class ChatController {

//    @CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4202"}, maxAge = 4800, allowCredentials = "false")
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public Message sendMessage(@Payload final Message message) throws Exception {
        return message;
    }
}
