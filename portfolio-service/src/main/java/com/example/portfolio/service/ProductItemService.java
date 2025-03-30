package com.example.portfolio.service;

import com.example.portfolio.entity.ProductItem;
import com.example.portfolio.repository.ProductItemRepository;
import com.example.portfolio.util.Result;
import com.example.portfolio.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class ProductItemService {

    private final ProductItemRepository repository;

    public ProductItemService(ProductItemRepository repository) {
        this.repository = repository;
    }

    // 创建
    public Result<ProductItem> create(ProductItem item) {

        String productGroupId = item.getProductGroupId();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String format = String.format("PF%s%04d", today, Integer.parseInt(productGroupId));
        item.setProductGroupId(format);

        return Result.build(repository.save(item), ResultCodeEnum.SUCCESS);
    }

    // 更新
    public Result<ProductItem> update(ProductItem item) {
        String productGroupId = item.getProductGroupId();
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String format = String.format("PF%s%04d", today, Integer.parseInt(productGroupId));
        item.setProductGroupId(format);

        return repository.findById(item.getProductItemId())
                .map(existing -> Result.build(repository.save(item), ResultCodeEnum.SUCCESS))
                .orElseGet(() -> Result.build(null, ResultCodeEnum.DATA_ERROR));
    }

    // 删除
    public Result<Void> delete(Integer id) {
        return repository.findById(id)
                .map(item -> {
                    repository.delete(item);
                    return Result.build(null, ResultCodeEnum.SUCCESS);
                })
                .orElseGet(() -> Result.build(null, ResultCodeEnum.DATA_ERROR));
    }

    // 分页查询（按group_id）
    public Result<Page<ProductItem>> searchByGroupId(String groupId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Result.build(repository.findByProductGroupIdContaining(groupId, pageable), ResultCodeEnum.SUCCESS);
    }

    // 分页查询（按group_name）
    public Result<Page<ProductItem>> searchByGroupName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Result.build(repository.findByProductGroupNameContaining(name, pageable), ResultCodeEnum.SUCCESS);
    }

    public Result<List<ProductItem>> getByGroupId(String groupId) {
        /*判断groupID是否存在*/
        if (!repository.existsByProductGroupId(groupId)) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return Result.build(repository.findByProductGroupId(groupId), ResultCodeEnum.SUCCESS);
    }

}