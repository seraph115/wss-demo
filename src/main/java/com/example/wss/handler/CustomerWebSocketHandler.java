package com.example.wss.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionManager sessionManager;
    private final ObjectMapper objectMapper;

    public CustomerWebSocketHandler(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionManager.addSession(session); // 新的 WebSocket 连接建立时，添加会话到管理器
        System.out.println("WebSocket connection established: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session); // 连接关闭时，从管理器移除会话
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    public void sendCustomerToClient(Long customerId, Object customer) {
        sessionManager.getSessions().forEach(session -> {
            if (session.isOpen()) {
                try {
                    // 创建包含时间戳和客户信息的消息
                    Map<String, Object> message = new HashMap<>();
                    message.put("timestamp", LocalDateTime.now().toString()); // 添加时间戳
                    message.put("customer", customer);

                    // 将消息转换为 JSON 格式并发送给客户端
                    String json = objectMapper.writeValueAsString(message);
                    session.sendMessage(new TextMessage(json));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        // 这里可以处理来自客户端的消息
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Transport error in session: " + session.getId());
        exception.printStackTrace();
        sessionManager.removeSession(session); // 如果传输错误，移除会话并关闭
        session.close(CloseStatus.SERVER_ERROR);
    }
}