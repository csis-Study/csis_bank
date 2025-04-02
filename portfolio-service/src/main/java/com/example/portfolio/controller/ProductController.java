package com.example.portfolio.controller;


import com.example.portfolio._enum.ProductType;
import com.example.portfolio.dto.ProductHistoryDTO;
import com.example.portfolio.entity.Product;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.util.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    // CRUD
    // 新增产品
    @PostMapping
    public Result<Product> create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // 更新产品
    @PutMapping
    public Result<Product> update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    // 删除产品
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    // 按类型分页查询
    @GetMapping("/type")
    public Result<Page<Product>> getByType(
            @RequestParam ProductType type,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getByType(type, page, size);
    }

    // 按名称模糊查询
    @GetMapping("/search")
    public Result<Page<Product>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.searchByName(name, page, size);
    }
}