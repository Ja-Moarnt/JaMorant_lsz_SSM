package com.JaMorant.SSM.vod.goodvideo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:JaMorant
 * @time:2023/3/5 22:45
 * @explain:
 */
@Component
public class MyHandler implements WebSocketHandler {

    private static final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接建立时执行的操作
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 收到消息时执行的操作
        String payload = message.getPayload().toString();
        session.sendMessage(new TextMessage("Received: " + payload));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 传输错误时执行的操作
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 连接关闭时执行的操作
        sessions.remove(session);
    }

    public static List<WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}

