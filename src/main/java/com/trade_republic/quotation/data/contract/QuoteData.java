package com.trade_republic.quotation.data.contract;

import java.math.BigDecimal;

public interface QuoteData {

    BigDecimal getPrice();
    String getIsin();

    void setPrice(java.math.BigDecimal price);
    void setIsin(String isin);

    QuoteData map(QuoteData quoteData);
}
