package xyz.lib.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.lib.bookstore.config.ApplicationPropertiesConfig;
import xyz.lib.bookstore.dto.ChatMessageDTO;
import xyz.lib.bookstore.service.WebSocketMessageService;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:21
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service @Primary
public class WebSocketMessageServiceImpl implements WebSocketMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessageServiceImpl.class);
    private final ApplicationPropertiesConfig applicationProperties;
    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketMessageServiceImpl(ApplicationPropertiesConfig applicationProperties, SimpMessagingTemplate template) {
        this.applicationProperties = applicationProperties;
        this.template = template;
    }

    @Async
    public void sendChatMessage(ChatMessageDTO message) {
        template.convertAndSend(applicationProperties.getTopic().getMessage(), message);
    }

    @Async
    public void sendMessageCount(Integer totalMessage) {
        LOGGER.info("Total Messages: {}", totalMessage);
        template.convertAndSend(applicationProperties.getTopic().getCount(), totalMessage);
    }
}
