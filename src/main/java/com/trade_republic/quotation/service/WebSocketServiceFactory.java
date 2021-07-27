package com.trade_republic.quotation.service;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.contract.QuoteData;
import com.trade_republic.quotation.data.enums.StompMessageTypes;
import com.trade_republic.quotation.data.model.StompMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceFactory {

    @Autowired private InstrumentService instrumentService;
    @Autowired private QuoteService quoteService;

    public synchronized void processStompMessage(StompMessage stompMessage) {
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
