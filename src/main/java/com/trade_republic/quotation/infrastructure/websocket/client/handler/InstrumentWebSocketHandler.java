package com.trade_republic.quotation.infrastructure.websocket.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade_republic.quotation.data.model.instrument.InstrumentStompMessage;
import com.trade_republic.quotation.service.InstrumentService;
import com.trade_republic.quotation.service.WebSocketServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component("instrumentHandler")
public class InstrumentWebSocketHandler implements WebSocketHandler {

    @Autowired private WebSocketServiceFactory webSocketServiceFactory;
    private final Logger logger = LogManager.getLogger(InstrumentWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        logger.info("New session established : " + webSocketSession.getId());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        InstrumentStompMessage msg = new ObjectMapper().readValue(
                (String) webSocketMessage.getPayload(),
                InstrumentStompMessage.class
        );
        logger.info("Instrument Message received successfully: " + msg);
        webSocketServiceFactory.processStompMessage(msg);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        logger.error("Got an exception", throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) { }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
