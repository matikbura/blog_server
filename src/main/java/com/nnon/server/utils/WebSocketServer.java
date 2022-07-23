package com.nnon.server.utils;

import com.alibaba.fastjson.JSON;
import com.nnon.server.pojo.SocketKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {

    public static HashMap<SocketKey, Session> map = new HashMap<>();



    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        try {
            SocketKey socketKey = new SocketKey();
            socketKey.setUid(Integer.parseInt(TokenUtil.getUid(token)));
            socketKey.setSessionId(session.getId());
            map.put(socketKey, session);
            System.out.println(map);
            System.out.println(socketKey + "开启了连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {

        System.out.println(session.getId() + "关闭了连接");
        System.out.println(map);
        session.getId();
        for (SocketKey key : map.keySet()) {
            if (key.getSessionId().equals(session.getId())) {
                System.out.println(key + "关闭了连接");
                map.remove(key);
            }
        }


    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到客户端的消息:" + message);
        session.getBasicRemote().sendText(message);
    }

    /**
     * 连接错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(session.getRequestURI());
        System.out.println("出错啦");
    }

    /**
     * 发送指定人
     */
    public <T> void sendInfo(Integer uid, T message) {
        for (SocketKey key : map.keySet()) {
            if (Objects.equals(key.getUid(), uid)) {
                Session session = map.get(key);
                try {
                    String msg = JSON.toJSONString(message);
                    System.out.println(key + "发送消息" + msg);
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                }
            }
        }
    }

}
