package xyz.lib.bookstore.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import xyz.lib.bookstore.listener.RedisReceiver;
import xyz.lib.bookstore.service.WebSocketMessageService;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.config
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 18:17
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
public class RedisConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            @Qualifier("chatMessageListenerAdapter") MessageListenerAdapter chatMessageListenerAdapter,
                                            @Qualifier("countListenerAdapter") MessageListenerAdapter countListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(chatMessageListenerAdapter,  new PatternTopic("chat"));
        container.addMessageListener(countListenerAdapter, new PatternTopic("count"));
        return container;
    }

    @Bean("chatMessageListenerAdapter")
    MessageListenerAdapter chatMessageListenerAdapter(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveChatMessage");
    }

    @Bean("countListenerAdapter")
    MessageListenerAdapter countListenerAdapter(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveCountMessage");
    }

    @Bean
    RedisReceiver receiver(WebSocketMessageService webSocketMessageService) {
        return new RedisReceiver(webSocketMessageService);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean // Redis Atomic Counter to store no. of total messages sent from multiple app instances.
    RedisAtomicInteger getChatMessageCounter(RedisTemplate redisTemplate){
        RedisAtomicInteger chatMessageCounter = new RedisAtomicInteger("total-chat-message", redisTemplate.getConnectionFactory());
        return chatMessageCounter;
    }

}
