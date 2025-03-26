package com.example.portfolio.repository;

import com.example.portfolio._enum.ProductType;
import com.example.portfolio.entity.Product;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// ProductRepository.java
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    // 根据类型查询
    List<Product> findByProductType(ProductType productType);

    // 模糊查询（包含大小写不敏感）
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByProductNameContaining(@Param("name") String name);
}