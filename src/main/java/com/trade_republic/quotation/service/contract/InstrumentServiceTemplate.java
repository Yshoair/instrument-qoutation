package com.trade_republic.quotation.service.contract;

import com.trade_republic.quotation.data.contract.InstrumentData;
import com.trade_republic.quotation.data.model.instrument.CandlestickPresentationModel;
import com.trade_republic.quotation.data.model.instrument.InstrumentPresentationModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InstrumentServiceTemplate {

    void addInstrument(InstrumentData instrumentData);

    @Transactional
    void deleteInstrument(InstrumentData instrumentData);

    List<InstrumentPresentationModel> getLatestInstrumentsQuote();

    List<CandlestickPresentationModel> getHistoricalInstrumentsQuotes(String isin);
}
