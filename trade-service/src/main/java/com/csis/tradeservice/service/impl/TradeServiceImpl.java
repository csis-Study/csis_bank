package com.csis.tradeservice.service.impl;

import com.csis.tradeservice.pojo.Trade;
import com.csis.tradeservice.repository.TradeRepository;
import com.csis.tradeservice.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("TradeServiceImpl")
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    // 创建或更新交易
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);  // 该方法会根据主键判断是更新还是插入
    }

    // 根据 ID 查找交易
    public Optional<Trade> getTradeById(String tradeId) {
        return tradeRepository.findById(tradeId);
    }

    // 获取所有交易
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    // 删除交易
    public void deleteTrade(String tradeId) {
        tradeRepository.deleteById(tradeId);
    }

    // 根据客户 ID 获取所有交易
//    public List<Trade> getTradesByClientId(String clientId) {
//        return tradeRepository.findAllByClientId(clientId);
//    }
}