package com.csis.tradeservice.controller;

import com.csis.tradeservice.Client.ApprovalFeignClient;
import com.csis.tradeservice.pojo.Trade;
import com.csis.tradeservice.service.TradeService;
import com.csis.tradeservice.util.Result;
import com.csis.tradeservice.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trade")
public class Tradecontroller {
    @Autowired
    private TradeService tradeService;
    @Autowired
    private ApprovalFeignClient approvalFeignClient;
    // 创建交易
    @PostMapping
    public Result createTrade(@RequestBody Trade trade) {
        Trade createdTrade = tradeService.saveTrade(trade);
        return Result.build(createdTrade, ResultCodeEnum.SUCCESS);
    }

    // 更新交易
    @PutMapping("/{tradeId}")
    public ResponseEntity<Trade> updateTrade(@PathVariable String tradeId, @RequestBody Trade trade) {
        // 判断交易是否存在
        Optional<Trade> existingTrade = tradeService.getTradeById(tradeId);
        if (existingTrade.isPresent()) {
            trade.setTradeId(tradeId);  // 保证更新时使用相同的 ID
            Trade updatedTrade = tradeService.saveTrade(trade);
            return new ResponseEntity<>(updatedTrade, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 根据 ID 获取交易
    @GetMapping("/{tradeId}")
    public ResponseEntity<Trade> getTradeById(@PathVariable String tradeId) {
        Optional<Trade> trade = tradeService.getTradeById(tradeId);
        return trade.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 获取所有交易
    @GetMapping("/getAll")
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> trades = tradeService.getAllTrades();
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }

    // 删除交易
    @DeleteMapping("/{tradeId}")
    public ResponseEntity<Void> deleteTrade(@PathVariable String tradeId) {
        Optional<Trade> trade = tradeService.getTradeById(tradeId);
        if (trade.isPresent()) {
            tradeService.deleteTrade(tradeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 根据客户 ID 获取交易
//    @GetMapping("/client/{clientId}")
//    public ResponseEntity<List<Trade>> getTradesByClientId(@PathVariable String clientId) {
//        List<Trade> trades = tradeService.getTradesByClientId(clientId);
//        return new ResponseEntity<>(trades, HttpStatus.OK);
//    }
}
