package com.trade_republic.quotation.repository.contract;

import com.trade_republic.quotation.data.entity.Instrument;
import com.trade_republic.quotation.data.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuoteRepository extends JpaRepository<Integer, Quote> {
}
