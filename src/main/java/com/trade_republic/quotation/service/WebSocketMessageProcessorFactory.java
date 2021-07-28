package com.trade_republic.quotation.service;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.contract.QuoteData;
import com.trade_republic.quotation.data.enums.StompMessageTypes;
import com.trade_republic.quotation.data.model.StompMessage;
import com.trade_republic.quotation.service.contract.InstrumentServiceTemplate;
import com.trade_republic.quotation.service.contract.QuoteServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageProcessorFactory {

    @Autowired private InstrumentServiceTemplate instrumentService;
    @Autowired private QuoteServiceTemplate quoteService;

    /**
     * Entry point synchronized StompMessage processor used by the websocket handlers to ensure no racing
     * conditions arises and insures thread safety
     *
     * switch case on the message type and calls the required service operation accordingly
     *
     * @param stompMessage parsed stompMessage according to the handler calling the processor
     */
    public synchronized void processStompMessage(StompMessage<?> stompMessage) {
        final String messageType = stompMessage.getType();
        final StompMessageTypes type = StompMessageTypes.fromValue(messageType);
        if (type != null)
            switch (type) {
                case ADD:
                    instrumentService.addInstrument((InstrumentData) stompMessage.getData());
                    break;
                case DELETE:
                    instrumentService.deleteInstrument((InstrumentData) stompMessage.getData());
                    break;
                case QUOTE:
                    quoteService.addQuote((QuoteData) stompMessage.getData());
                    break;
            }
    }
}
