package xyz.lib.bookstore.service;

import xyz.lib.bookstore.dto.ChatMessageDTO;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:27
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface WebSocketMessageService {
    void sendChatMessage(ChatMessageDTO message);
    void sendMessageCount(Integer totalMessage);
}
