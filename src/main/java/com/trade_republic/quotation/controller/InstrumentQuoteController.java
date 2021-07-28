package com.trade_republic.quotation.controller;

import com.trade_republic.quotation.data.model.instrument.InstrumentPresentationModel;
import com.trade_republic.quotation.service.contract.InstrumentServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("instruments")
public class InstrumentQuoteController {

    @Autowired InstrumentServiceTemplate instrumentService;

    /**
     * REST-API that allows a User to retrieve all currently available Instruments with the latest price
     *
     * @return list of available instruments with the latest price
     */
    @GetMapping("quote")
    public ResponseEntity<List<InstrumentPresentationModel>> getLatestInstrumentsQuote() {
        return ResponseEntity.ok(instrumentService.getLatestInstrumentsQuote());
    }

    /**
     * REST-API that allows a User to retrieve all price history of a certain instrument in the for of candlestick data
     *
     * @param isin Internation securities identification number for a certain instrument
     * @return List of candlestick data for a certain instrument for the last half an hour
     */
    @GetMapping("{isin}/quotes")
    public ResponseEntity<?> getHistoricalInstrumentsQuotes(@PathVariable(name = "isin") String isin) {
        return ResponseEntity.ok(instrumentService.getHistoricalInstrumentsQuotes(isin));
    }
}
