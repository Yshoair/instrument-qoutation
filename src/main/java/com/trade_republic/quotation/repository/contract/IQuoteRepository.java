package com.trade_republic.quotation.repository.contract;

import com.trade_republic.quotation.data.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IQuoteRepository extends JpaRepository<QuoteEntity, Integer> {

    @Transactional @Modifying
    @Query(value = "DELETE FROM QuoteEntity q where q.instrument.isin = :isin")
    void deleteAllByIsin(@Param("isin") String isin);
}
