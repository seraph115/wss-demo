package com.example.wss.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CustomerWebSocketHandler customerWebSocketHandler;

    public WebSocketConfig(CustomerWebSocketHandler customerWebSocketHandler) {
        this.customerWebSocketHandler = customerWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customerWebSocketHandler, "/wss/customer")
                .setAllowedOrigins("*");
    }

}

