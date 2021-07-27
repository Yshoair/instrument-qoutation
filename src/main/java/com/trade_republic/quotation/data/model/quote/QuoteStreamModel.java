package com.trade_republic.quotation.data.model.quote;

import com.trade_republic.quotation.data.contract.QuoteData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteStreamModel implements QuoteData {
    private BigDecimal price;
    private String isin;

    @Override
    public QuoteData map(QuoteData quoteData) {
        return null;
    }
}
