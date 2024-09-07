package com.example.wss.config;


import com.example.wss.handler.CustomerWebSocketHandler;
import com.example.wss.handler.JwtHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CustomerWebSocketHandler customerWebSocketHandler;

    @Autowired
    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;

    public WebSocketConfig(CustomerWebSocketHandler customerWebSocketHandler, JwtHandshakeInterceptor jwtHandshakeInterceptor) {
        this.customerWebSocketHandler = customerWebSocketHandler;
        this.jwtHandshakeInterceptor = jwtHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customerWebSocketHandler, "/wss/customer").addInterceptors(jwtHandshakeInterceptor).setAllowedOrigins("*");
    }

}
