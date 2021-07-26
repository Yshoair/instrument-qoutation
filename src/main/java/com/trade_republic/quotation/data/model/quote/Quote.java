package com.trade_republic.quotation.data.model.quote;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Quote {
    private BigDecimal price;
    private String isin;
}
