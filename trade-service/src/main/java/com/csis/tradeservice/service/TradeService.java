package com.csis.tradeservice.service;

import com.csis.tradeservice.pojo.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    Trade saveTrade(Trade trade);
    Optional<Trade> getTradeById(String tradeId);
    List<Trade> getAllTrades();
    void deleteTrade(String tradeId);
//    public List<Trade> getTradesByClientId(String clientId);
}
