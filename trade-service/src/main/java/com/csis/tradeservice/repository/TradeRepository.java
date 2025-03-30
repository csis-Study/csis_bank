package com.csis.tradeservice.repository;

import com.csis.tradeservice.pojo.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tradeRepository")
public interface TradeRepository extends JpaRepository<Trade, String> {
    // 你可以在这里添加自定义的查询方法
}