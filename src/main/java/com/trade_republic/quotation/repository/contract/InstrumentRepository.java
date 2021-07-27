package com.trade_republic.quotation.repository.contract;

import com.trade_republic.quotation.data.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, String> {
}
