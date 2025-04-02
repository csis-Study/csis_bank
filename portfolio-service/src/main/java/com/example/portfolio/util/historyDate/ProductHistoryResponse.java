package com.example.portfolio.util.historyDate;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductHistoryResponse {
    private String productName;
    private String dataType;
    private List<HistoryDataPoint> timeSeries;
}