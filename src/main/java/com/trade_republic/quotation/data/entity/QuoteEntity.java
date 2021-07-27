package com.trade_republic.quotation.data.entity;

import com.trade_republic.quotation.data.contract.QuoteData;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "quotes")
public class QuoteEntity implements QuoteData {

    @Id @Column(name = "id")
    @SequenceGenerator(name = "quotes_ids", sequenceName = "quotes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_ids")
    private int id;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "priced_on")
    private Date pricedOn;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "instrument_isin", referencedColumnName = "isin")
    private InstrumentEntity instrument;

    @Override
    public String getIsin() {
        return instrument != null ? instrument.getIsin() : "";
    }

    @Override
    public void setIsin(String isin) {
        instrument = new InstrumentEntity(isin);
    }

    @Override
    public QuoteData map(QuoteData quoteData) {
        price = quoteData.getPrice();
        pricedOn = new Date();
        instrument = new InstrumentEntity(quoteData.getIsin());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuoteEntity)) return false;
        QuoteEntity quote = (QuoteEntity) o;
        return id == quote.id && price.equals(quote.price) && pricedOn.equals(quote.pricedOn) &&
                instrument.equals(quote.instrument);
    }
}
