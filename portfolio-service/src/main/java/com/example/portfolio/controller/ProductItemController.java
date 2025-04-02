package com.example.portfolio.controller;


import com.example.portfolio.entity.ProductItem;
import com.example.portfolio.service.ProductItemService;
import com.example.portfolio.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product-items")
@RequiredArgsConstructor
public class ProductItemController {
    private final ProductItemService service;


    //产品组合总值结算接口
    @GetMapping("/{groupId}/total-value")
    public ResponseEntity<?> getTotalValue(@PathVariable String groupId) {
        try {
            BigDecimal total = service.calculateTotalValue(groupId);
            return ResponseEntity.ok(Collections.singletonMap("totalValue", total));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    //单个产品总值计算接口
    @GetMapping("/{groupId}/single-value/{productId}")
    public ResponseEntity<?> getProductValue(
            @PathVariable String groupId,
            @PathVariable Integer productId) {
        try {
            BigDecimal value = service.calculateProductValue(groupId, productId);
            return ResponseEntity.ok(value);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }



    // CRUD
    @PostMapping
    public Result<ProductItem> create(@RequestBody ProductItem item) {
        return service.create(item);
    }

    @PutMapping
    public Result<ProductItem> update(@RequestBody ProductItem item) {
        return service.update(item);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/group-id")
    public Result<Page<ProductItem>> searchByGroupId(
            @RequestParam String groupId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.searchByGroupId(groupId, page, size);
    }

    @GetMapping("/{groupId}")
    public Result <List<ProductItem>> getByGroupId(@PathVariable String groupId) {
        return service.getByGroupId(groupId);
    }

    @GetMapping("/group-name")
    public Result<Page<ProductItem>> searchByGroupName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.searchByGroupName(name, page, size);
    }
}