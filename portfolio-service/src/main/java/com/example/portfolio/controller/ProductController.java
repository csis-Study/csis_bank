package com.example.portfolio.controller;


import com.example.portfolio._enum.ProductType;
import com.example.portfolio.entity.Product;
import com.example.portfolio.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable String productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Product>> getByType(@PathVariable ProductType type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping("/name/{keyword}")
    public ResponseEntity<List<Product>> searchByName(@PathVariable String keyword) {
        return ResponseEntity.ok(productService.searchProductsByName(keyword));
    }
}