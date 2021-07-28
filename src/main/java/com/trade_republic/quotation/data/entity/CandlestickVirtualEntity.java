package com.trade_republic.quotation.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @ToString
@Entity @IdClass(CandlestickVirtualEntity.CandlestickPK.class)
@Table(name = "instrument_candlesticks")
public class CandlestickVirtualEntity {

    @Id @Column(name = "isin")
    private String isin;
    @Id @Column(name = "open_timestamp")
    private Date openTimestamp;
    @Column(name = "open_price")
    private BigDecimal openPrice;
    @Column(name = "high_price")
    private BigDecimal highPrice;
    @Column(name = "low_price")
    private BigDecimal lowPrice;
    @Column(name = "close_price")
    private BigDecimal closePrice;
    @Column(name = "close_timestamp")
    private Date closeTimestamp;

    public static class CandlestickPK implements Serializable {
        private String isin;
        private Date openTimestamp;

        public CandlestickPK() { }

        public CandlestickPK(String isin, Date openTimestamp) {
            this.isin = isin;
            this.openTimestamp = openTimestamp;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CandlestickPK)) return false;
            CandlestickPK that = (CandlestickPK) o;
            return isin.equals(that.isin) && openTimestamp.equals(that.openTimestamp);
        }

        @Override public int hashCode() {
            return Objects.hash(isin, openTimestamp);
        }
    }
}
