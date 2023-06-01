package com.JaMorant.SSM.thc.config;

import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Service
//sessionId指定当前连接的用户将要给其他哪个用户发消息
@ServerEndpoint("/s/{sessionId}")
public class WebSocketServiceImpl{

    private static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<>();

    //建立连接
    @OnOpen
    public void onOpen(Session session){
        System.out.println("onOpen()" + session.getId());
        map.put(session.getId(),session);
    }

    //关闭连接
    @OnClose
    public void onClose(Session session){
        System.out.println("onClose()" + session.getId());
        map.remove(session.getId());
    }

    //服务端收到客户端发送的消息
    @OnMessage
    public void onMessage(@PathParam("sessionId")String sessionId , String message, Session session) throws IOException {
        System.out.println("onMessage(),用户发送的信息为:" + message + ",sessionId=" + session.getId());
        //发送给sessionId为2的用户
        Session target=map.get(String.valueOf(sessionId));
        if(target!=null){
			//同步发送
            target.getBasicRemote().sendText(message+"\n");
			//异步发送
			//target.getAsyncRemote().sendText(message+"\n");
        }
    }
    // 发送给指定sessionId的用户
    public void sendMessageToUser(String sessionId, String message) throws IOException {
        Session target = map.get(sessionId);
        if (target != null) {
            // 同步发送
            target.getBasicRemote().sendText(message);
            // 异步发送
            // target.getAsyncRemote().sendText(message);
        }
    }

    //服务端产生错误
    @OnError
    public void onError(Throwable error){
        System.out.println("onError(),message=" + error.getMessage());
    }

}
