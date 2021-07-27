package com.trade_republic.quotation.service;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.entity.InstrumentEntity;
import com.trade_republic.quotation.repository.contract.IInstrumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InstrumentService {

    @Autowired private IInstrumentRepository instrumentRepository;
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
}
