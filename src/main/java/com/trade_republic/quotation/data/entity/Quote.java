package com.trade_republic.quotation.data.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity @Table(name = "quotes")
public class Quote {

    @Id @Column(name = "id")
    private int id;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "priced_on")
    private Date pricedOn;
    @ToString.Exclude
    @ManyToOne @JoinColumn(name = "instrument_isin", referencedColumnName = "isin")
    private Instrument instrument;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;
        Quote quote = (Quote) o;
        return id == quote.id && price.equals(quote.price) && pricedOn.equals(quote.pricedOn) &&
                instrument.equals(quote.instrument);
    }
}
