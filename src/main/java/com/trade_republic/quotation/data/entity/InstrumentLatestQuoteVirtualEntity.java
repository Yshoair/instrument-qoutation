package com.trade_republic.quotation.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter @Setter
@Entity
@Table(name = "instruments_latest_quotes")
public class InstrumentLatestQuoteVirtualEntity {

    @Id @Column(name = "isin")
    private String isin;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "priced_on")
    private Date pricedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstrumentLatestQuoteVirtualEntity)) return false;
        InstrumentLatestQuoteVirtualEntity that = (InstrumentLatestQuoteVirtualEntity) o;
        return isin.equals(that.isin)
                && description.equals(that.description)
                && Objects.equals(price, that.price)
                && Objects.equals(pricedOn, that.pricedOn);
    }
}
