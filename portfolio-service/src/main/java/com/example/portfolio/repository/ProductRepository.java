package com.example.portfolio.repository;

import com.example.portfolio._enum.ProductStatus;
import com.example.portfolio._enum.ProductType;
import com.example.portfolio.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {



    // 根据类型分页查询
    Page<Product> findByProductType(ProductType productType, Pageable pageable);

    // 根据名称模糊查询
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    List<Product> findByStatus(ProductStatus status);

}