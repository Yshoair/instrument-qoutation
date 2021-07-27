package com.trade_republic.quotation.data.contract;

public interface InstrumentData {

    String getIsin();
    String getDescription();

    void setIsin(String isin);
    void setDescription(String description);

    InstrumentData map(InstrumentData instrumentData);
}
