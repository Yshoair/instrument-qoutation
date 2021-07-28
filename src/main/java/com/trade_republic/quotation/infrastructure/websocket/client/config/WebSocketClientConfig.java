package com.trade_republic.quotation.infrastructure.websocket.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public abstract class WebSocketClientConfig {

    private final String uriTemplate;
    private final WebSocketHandler handler;

    public WebSocketClientConfig(String uriTemplate, WebSocketHandler handler) {
        this.uriTemplate = uriTemplate;
        this.handler = handler;
    }

    public WebSocketConnectionManager webSocketConnectionManagerFactory() {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                handler,
                uriTemplate);
        manager.setAutoStartup(true);
        return manager;
    }
}
