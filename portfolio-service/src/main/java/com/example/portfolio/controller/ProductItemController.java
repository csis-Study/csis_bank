package com.example.portfolio.controller;


import com.example.portfolio.entity.ProductItem;
import com.example.portfolio.service.ProductItemService;
import com.example.portfolio.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-items")
//@RequiredArgsConstructor
public class ProductItemController {

    private final ProductItemService service;

    public ProductItemController(ProductItemService service) {
        this.service = service;
    }

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

    @GetMapping("/group-name")
    public Result<Page<ProductItem>> searchByGroupName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.searchByGroupName(name, page, size);
    }

    @GetMapping("/{groupId}")
    public Result <List<ProductItem>> getByGroupId(@PathVariable String groupId) {
        return service.getByGroupId(groupId);
    }
}