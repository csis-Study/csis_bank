package com.csis.approvalservice.fegin.cilent;

import com.csis.tradeservice.pojo.Trade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "trade-service")
public interface tradeFeignClient {
    @PostMapping("/trade")
    ResponseEntity<Trade> createTrade(@RequestBody Trade trade);
    @PutMapping("/{tradeId}")
    ResponseEntity<Trade> updateTrade(@PathVariable String tradeId, @RequestBody Trade trade);
    @GetMapping("/{tradeId}")
    ResponseEntity<Trade> getTradeById(@PathVariable String tradeId);
    @GetMapping("/trade/getAll")
    ResponseEntity<List<Trade>> getAllTrades();
    @DeleteMapping("/{tradeId}")
    ResponseEntity<Void> deleteTrade(@PathVariable String tradeId);
}
