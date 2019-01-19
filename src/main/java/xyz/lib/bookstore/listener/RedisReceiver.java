package xyz.lib.bookstore.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.lib.bookstore.dto.ChatMessageDTO;
import xyz.lib.bookstore.service.WebSocketMessageService;

import java.io.IOException;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.listener
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:20
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class RedisReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);

    private final WebSocketMessageService webSocketMessageService;

    @Autowired
    public RedisReceiver(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    // Invoked when message is publish to "chat" channel
    public void receiveChatMessage(String message) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessageDTO chatMessage = objectMapper.readValue(message, ChatMessageDTO.class);

        LOGGER.info("Notification Message Received: " + chatMessage);
        webSocketMessageService.sendChatMessage(chatMessage);

    }

    // Invoked when message is publish to "count" channel
    public void receiveCountMessage(String totalMessageCount) {

        LOGGER.info("Count Message Received :" + totalMessageCount);
        webSocketMessageService.sendMessageCount(Integer.parseInt(totalMessageCount));

    }
}
