package com.trade_republic.quotation.data.model.instrument;

import com.trade_republic.quotation.data.contract.InstrumentData;
import lombok.Data;

@Data
public class Instrument implements InstrumentData {
    private String isin;
    private String description;

    @Override
    public InstrumentData map(InstrumentData instrumentData) {
        return null;
    }
}
