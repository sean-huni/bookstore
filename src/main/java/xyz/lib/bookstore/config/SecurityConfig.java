package xyz.lib.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;

import java.util.concurrent.ConcurrentHashMap;

import static xyz.lib.bookstore.constants.Constants.PUBLIC_MATCHERS;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.config
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:14
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
@EnableWebFluxSecurity
//@EnableSpringWebSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private EncryptionConfig encryptionConfig;

    @Autowired
    public SecurityConfig(EncryptionConfig encryptionConfig) {
        this.encryptionConfig = encryptionConfig;
    }

    // @formatter:off
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .cors().disable()
                .httpBasic().securityContextRepository(new WebSessionServerSecurityContextRepository())
                .and().authorizeExchange().pathMatchers(PUBLIC_MATCHERS).permitAll()
                .and().authorizeExchange().pathMatchers(HttpMethod.GET, "/v1/books").permitAll();

        return http.build();
    }
    // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return encryptionConfig.passwordEncoder();
    }

    @Bean
    public ReactiveSessionRepository httpSessionStrategy() {
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }
}
