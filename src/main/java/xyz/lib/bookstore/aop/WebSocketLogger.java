package xyz.lib.bookstore.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Arrays;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.aop
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 17:47
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Aspect
@Component
public class WebSocketLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketLogger.class);

    @Pointcut("execution(* xyz.lib.bookstore.config.*.onSocketConnected(..))")
    private void onConnectPointCut() {}
//  && args(yourString,..)
    @Pointcut("execution(* xyz.lib.bookstore.config.*.onSocketDisconnected(..))")
    private void onDisconnectPointCut() {}

    @Before("onConnectPointCut()")
    private void logBeforeConnection(JoinPoint joinPoint) {
        LOGGER.info("Before Connection...");
        log(joinPoint);

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SessionConnectedEvent) {
                final SessionConnectedEvent event = (SessionConnectedEvent) arg;
                final StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
                LOGGER.info("WebSocket Session Connected: {}", event.getMessage());
                LOGGER.info("Connect event [sessionId: {} ]", sha.getSessionId());
            }
        }
    }

    @Before("onDisconnectPointCut()")
    private void logBeforeDisconnection(JoinPoint joinPoint) {
        LOGGER.info("Before Disconnection...");
        log(joinPoint);

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SessionDisconnectEvent) {
                final SessionDisconnectEvent event = (SessionDisconnectEvent) arg;
                final StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
                LOGGER.info("WebSocket Session Disconnected: {}", event.getMessage());
                LOGGER.info("DisConnect event [sessionId: {}]", sha.getSessionId());
            }
        }

    }

    private void log(JoinPoint joinPoint) {
        LOGGER.info("Entering in Method :  {}", joinPoint.getSignature().getName());
        LOGGER.info("Class Name :  {}", joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Arguments :  {}", Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("Target class : {}", joinPoint.getTarget().getClass().getName());
    }
}
