package com.github.ozlin.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/websocket")
public class MyWebSocket {

    private static final Logger LOGGER = Logger.getLogger(MyWebSocket.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("Connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("Received: " + message);
        try {
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error sending message", e);
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("Disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOGGER.log(Level.SEVERE, "Error: " + session.getId(), throwable);
    }
}
