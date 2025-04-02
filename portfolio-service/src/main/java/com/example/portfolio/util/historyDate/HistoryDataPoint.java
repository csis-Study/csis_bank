package com.example.portfolio.util.historyDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class HistoryDataPoint {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal netValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal floatRate;
}