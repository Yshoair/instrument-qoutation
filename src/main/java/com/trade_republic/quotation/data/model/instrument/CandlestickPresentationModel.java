package com.trade_republic.quotation.data.model.instrument;

import com.trade_republic.quotation.data.entity.CandlestickVirtualEntity;
import com.trade_republic.quotation.infrastructure.util.DateTimeUtils;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CandlestickPresentationModel implements Serializable {
    private String isin;
    private String openTimestamp;
    private BigDecimal openPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal closePrice;
    private String closeTimestamp;

    public CandlestickPresentationModel mapFrom(CandlestickVirtualEntity candlestick, String datetimeFormat) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        isin = candlestick.getIsin();
        openTimestamp = dateTimeUtils.toStringFormat(candlestick.getOpenTimestamp(), datetimeFormat);
        openPrice = candlestick.getOpenPrice();
        highPrice = candlestick.getHighPrice();
        lowPrice = candlestick.getLowPrice();
        closePrice = candlestick.getClosePrice();
        closeTimestamp = dateTimeUtils.toStringFormat(candlestick.getCloseTimestamp(), datetimeFormat);
        return this;
    }
}
