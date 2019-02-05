package xyz.lib.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.config
 * USER      : sean
 * DATE      : 29-Sat-Dec-2018
 * TIME      : 09:13
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
@EnableRedisWebSession
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }
}
