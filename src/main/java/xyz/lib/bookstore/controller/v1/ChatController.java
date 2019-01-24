package xyz.lib.bookstore.controller.v1;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
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

@Controller
public class ChatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);
    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    //    @CrossOrigin(origins = {"http://localhost:4201", "http://localhost:4202"}, maxAge = 47800, allowCredentials = "false")
    @MessageMapping("/public")
    @SendTo("/topic/msg")
    public Message sendMessage(@Payload final Message message) throws Exception {
        LOGGER.info("From: {}", message);
        this.template.convertAndSend("/public", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
        return message;
    }

    @SendTo("/connect")
    public void connect(@Payload final String message) throws Exception {
        statusLog("connect", message);
    }

    @SendTo("/disconnect")
    public void disconnect(@Payload final String message) throws Exception {
        statusLog("disconnect", message);
    }

    private void statusLog(String status, String message) {
        LOGGER.info("Status: {}", message);
        String lMessage = new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message;
        Gson gson = new Gson();
        final String json = gson.toJson(lMessage, String.class);
        this.template.convertAndSend("/public", json);
    }

}
