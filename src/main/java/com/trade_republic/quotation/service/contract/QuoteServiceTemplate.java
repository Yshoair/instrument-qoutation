package com.trade_republic.quotation.service.contract;

import com.trade_republic.quotation.data.contract.QuoteData;

public interface QuoteServiceTemplate {

    void addQuote(QuoteData quoteData);

    void deleteQuotes(String isin);
}
