package com.example.portfolio;

import com.example.portfolio.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PortfolioApplicationTests {

    @Autowired
    private ProductService productService;

    //数据刷新测试
    @Test
    void testRefreshDailyData() {
        productService.refreshDailyData(); // 手动调用定时任务
        // 验证数据库数据是否更新
    }

    //查询历史数据测试
    @Test
    void testDateRangeCalculation() {
        LocalDate today = LocalDate.of(2025,3,28);
        LocalDate expectedStart = LocalDate.of(2025,3,22);

        assertEquals(expectedStart, today.minusDays(6)); // 验证7天跨度
    }
}
