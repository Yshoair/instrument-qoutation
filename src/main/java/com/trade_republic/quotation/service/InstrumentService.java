package com.trade_republic.quotation.service;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.entity.InstrumentEntity;
import com.trade_republic.quotation.data.entity.InstrumentLatestQuoteVirtualEntity;
import com.trade_republic.quotation.data.model.instrument.InstrumentPresentationModel;
import com.trade_republic.quotation.repository.contract.InstrumentLatestQuoteRepository;
import com.trade_republic.quotation.repository.contract.InstrumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InstrumentService {

    @Autowired private InstrumentRepository instrumentRepository;
    @Autowired private InstrumentLatestQuoteRepository instrumentLatestQuoteRepository;
    @Autowired private QuoteService quoteService;
    private final Logger logger = LogManager.getLogger(InstrumentService.class);

    public void addInstrument(InstrumentData instrumentData) {
        try {
            InstrumentData instrumentEntity = new InstrumentEntity().map(instrumentData);
            instrumentRepository.save((InstrumentEntity) instrumentEntity);
            instrumentRepository.flush();
        } catch (Exception e) {
            logger.error("Failed to add instrument with isin : " + instrumentData.getIsin() + " due to: " +
                    e.getMessage());
        }
    }

    @Transactional
    public void deleteInstrument(InstrumentData instrumentData) {
        try {
            quoteService.deleteQuotes(instrumentData.getIsin());
            instrumentRepository.deleteById(instrumentData.getIsin());
            instrumentRepository.flush();
        } catch (Exception e) {
            logger.error("Failed to delete instrument with isin " + instrumentData.getIsin() + " due to: " +
                    e.getMessage());
        }
    }

    public List<InstrumentPresentationModel> getLatestInstrumentsQuote() {
        List<InstrumentLatestQuoteVirtualEntity> instruments = instrumentLatestQuoteRepository.findAll();
        return instruments
                .stream()
                .map(instrumentEntity -> new InstrumentPresentationModel().mapFrom(instrumentEntity))
                .collect(Collectors.toList());
    }
}
