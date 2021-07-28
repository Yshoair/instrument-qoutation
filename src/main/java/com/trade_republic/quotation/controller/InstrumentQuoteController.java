package com.trade_republic.quotation.controller;

import com.trade_republic.quotation.data.model.instrument.InstrumentPresentationModel;
import com.trade_republic.quotation.service.InstrumentService;
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

    @Autowired InstrumentService instrumentService;

    @GetMapping("quote")
    public ResponseEntity<List<InstrumentPresentationModel>> getLatestInstrumentsQuote() {
        return ResponseEntity.ok(instrumentService.getLatestInstrumentsQuote());
    }

    @GetMapping("{isin}/quotes")
    public ResponseEntity<?> getHistoricalInstrumentsQuotes(@PathVariable(name = "isin") String isin) {
        return ResponseEntity.ok(instrumentService.getHistoricalInstrumentsQuotes(isin));
    }
}
