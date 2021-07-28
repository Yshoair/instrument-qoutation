package com.trade_republic.quotation.service;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.entity.CandlestickVirtualEntity;
import com.trade_republic.quotation.data.entity.InstrumentEntity;
import com.trade_republic.quotation.data.entity.InstrumentLatestQuoteVirtualEntity;
import com.trade_republic.quotation.data.model.instrument.CandlestickPresentationModel;
import com.trade_republic.quotation.data.model.instrument.InstrumentPresentationModel;
import com.trade_republic.quotation.infrastructure.config.ConfigurationManager;
import com.trade_republic.quotation.infrastructure.util.DateTimeUtils;
import com.trade_republic.quotation.repository.contract.InstrumentCandlesticksRepository;
import com.trade_republic.quotation.repository.contract.InstrumentLatestQuoteRepository;
import com.trade_republic.quotation.repository.contract.InstrumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InstrumentService {

    @Autowired private InstrumentRepository instrumentRepository;
    @Autowired private InstrumentLatestQuoteRepository instrumentLatestQuoteRepository;
    @Autowired private InstrumentCandlesticksRepository instrumentCandlesticksRepository;
    @Autowired private QuoteService quoteService;
    @Autowired private ConfigurationManager configManager;
    @Autowired private DateTimeUtils dateTimeUtils;
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

    public List<CandlestickPresentationModel> getHistoricalInstrumentsQuotes(String isin) {
        Date fromDateTime = dateTimeUtils.subtractFromDate(new Date(), configManager.getCandlestickInterval());
        List<CandlestickVirtualEntity> candlestickEntities =
                instrumentCandlesticksRepository.findByIsinAndOpenTimestampGreaterThanEqual(isin, fromDateTime);
        if (candlestickEntities.size() > 0) {
            return candlestickEntities
                    .stream()
                    .map(candlestickEntity -> new CandlestickPresentationModel().mapFrom(
                            candlestickEntity, configManager.getDateTimeFormat()))
                    .collect(Collectors.toList());
        } else {
            CandlestickVirtualEntity candlestickEntity =
                    instrumentCandlesticksRepository.findFirstByIsinOrderByOpenTimestampDesc(isin);
            return Collections.singletonList(new CandlestickPresentationModel().mapFrom(
                    candlestickEntity, configManager.getDateTimeFormat()));
        }
    }
}
