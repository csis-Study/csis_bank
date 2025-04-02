package com.example.portfolio.service;


import com.example.portfolio.dto.ProductHistoryDTO;
import com.example.portfolio.repository.ProductHistoryRepository;
import com.example.portfolio.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final ProductHistoryRepository historyRepository;
    private final ProductRepository productRepository;

    // 查询历史数据
    public List<ProductHistoryDTO> getProductHistory(Integer productId,
                                                     LocalDate startTime,
                                                     LocalDate endTime) {
        return historyRepository.findHistoryByProductAndDateRange(productId, startTime, endTime)
                .stream()
                .map(h -> new ProductHistoryDTO(h.getProduct().getProductId(),h.getProduct().getProductName(),h.getRecordDate(), h.getFloatRate(), h.getNetValue()))
                .collect(Collectors.toList());
    }
}
