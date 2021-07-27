package com.trade_republic.quotation.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity @Table(name = "instruments")
public class Instrument {

    @Id @Column(name = "isin")
    private String isin;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "added_on", nullable = false)
    private Date addedOn;
    @ToString.Exclude
    @OneToMany(mappedBy = "instrument")
    private Collection<Quote> quotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) return false;
        Instrument that = (Instrument) o;
        return isin.equals(that.isin)
                && description.equals(that.description)
                && addedOn.equals(that.addedOn)
                && Objects.equals(quotes, that.quotes);
    }
}
