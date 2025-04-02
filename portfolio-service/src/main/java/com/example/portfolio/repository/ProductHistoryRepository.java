package com.example.portfolio.repository;

import com.example.portfolio.entity.ProductHistory;
//import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// 历史数据仓库接口
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer> {

    @Query("SELECT h FROM ProductHistory h WHERE h.product.productId = :productId "
            + "AND h.recordDate BETWEEN :start AND :end ORDER BY h.recordDate")
    List<ProductHistory> findHistoryByProductAndDateRange(
            @Param("productId") Integer productId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);


}