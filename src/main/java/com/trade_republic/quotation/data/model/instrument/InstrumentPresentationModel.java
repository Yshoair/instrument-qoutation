package com.trade_republic.quotation.data.model.instrument;

import com.trade_republic.quotation.data.entity.InstrumentLatestQuoteVirtualEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class InstrumentPresentationModel implements Serializable {
    private String isin;
    private String description;
    private BigDecimal price;
    private Date pricedOn;

    public InstrumentPresentationModel mapFrom(InstrumentLatestQuoteVirtualEntity instrument) {
        isin = instrument.getIsin();
        description = instrument.getDescription();
        price = instrument.getPrice();
        pricedOn = instrument.getPricedOn();
        return this;
    }
}
