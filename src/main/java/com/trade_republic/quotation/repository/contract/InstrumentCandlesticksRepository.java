package com.trade_republic.quotation.repository.contract;

import com.trade_republic.quotation.data.entity.CandlestickVirtualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InstrumentCandlesticksRepository extends JpaRepository<CandlestickVirtualEntity,
                                                          CandlestickVirtualEntity.CandlestickPK> {

    @Query("select c from CandlestickVirtualEntity c where c.isin = :isin and c.openTimestamp >= :date")
    List<CandlestickVirtualEntity> findByIsinAndOpenTimestampGreaterThanEqual(@Param("isin") String isin,
                                                                              @Param("date") Date date);

    CandlestickVirtualEntity findFirstByIsinOrderByOpenTimestampDesc(@Param("isin") String isin);
}