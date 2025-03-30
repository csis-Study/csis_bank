package com.example.portfolio;

import com.example.portfolio.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PortfolioApplicationTests {

    //数据刷新测试
    @Autowired
    private ProductService productService;

    @Test
    void testRefreshDailyData() {
        productService.refreshDailyData(); // 手动调用定时任务
        // 验证数据库数据是否更新
    }
}
