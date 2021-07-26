package com.trade_republic.quotation.infrastructure.websocket.client.config;

import com.trade_republic.quotation.infrastructure.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Configuration
public class WebSocketInstrumentClientConfig extends WebSocketClientConfig {

    @Autowired
    public WebSocketInstrumentClientConfig(ConfigurationManager configurationManager,
                                           @Qualifier("instrumentHandler") WebSocketHandler webSocketHandler) {
        super(configurationManager.getInstrumentStream(), webSocketHandler);
    }

    @Bean("instrumentConnectionManager")
    @Override
    public WebSocketConnectionManager webSocketConnectionManagerFactory() {
        return super.webSocketConnectionManagerFactory();
    }
}
