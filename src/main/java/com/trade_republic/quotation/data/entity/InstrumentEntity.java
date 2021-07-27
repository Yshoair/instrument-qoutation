package com.trade_republic.quotation.data.entity;

import com.trade_republic.quotation.data.contract.InstrumentData;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "instruments")
public class InstrumentEntity implements InstrumentData {

    @Id @Column(name = "isin")
    private String isin;
    @Column(name = "description")
    private String description;
    @Column(name = "added_on")
    private Date addedOn;
    @ToString.Exclude
    @OneToMany(mappedBy = "instrument")
    private Collection<QuoteEntity> quotes;

    public InstrumentEntity(String isin) { this.isin = isin; }

    @Override
    public InstrumentData map(InstrumentData instrumentData) {
        isin = instrumentData.getIsin();
        description = instrumentData.getDescription();
        addedOn = new Date();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstrumentEntity)) return false;
        InstrumentEntity that = (InstrumentEntity) o;
        return isin.equals(that.isin)
                && description.equals(that.description)
                && addedOn.equals(that.addedOn)
                && Objects.equals(quotes, that.quotes);
    }
}
