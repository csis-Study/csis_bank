package com.example.portfolio.service;

import com.example.portfolio.entity.Product;
import com.example.portfolio.entity.ProductItem;
import com.example.portfolio.repository.ProductItemRepository;
import com.example.portfolio.util.Result;
import com.example.portfolio.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductItemService {
    private final ProductItemRepository repository;

    //产品组合总值结算方法
    @Transactional(readOnly = true)
    public BigDecimal calculateTotalValue(String productGroupId) {
        List<ProductItem> items = repository.findByProductGroupId(productGroupId);

        if (items.isEmpty()) {
            throw new IllegalArgumentException("产品组合不存在: " + productGroupId);
        }

        return items.stream().map(item -> {
                    Product product = item.getProduct();
                    if (product == null) {
                        throw new DataIntegrityViolationException("产品数据缺失，product_id: " + item.getProduct().getProductId());
                    }
                    return product.getNetValue().multiply(BigDecimal.valueOf(item.getAmount()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //单个产品总值结算方法
    @Transactional(readOnly = true)
    public BigDecimal calculateProductValue(String productGroupId, Integer productId) {
        // 查询产品项（精确匹配）
        ProductItem item = repository.findByProductGroupIdAndProduct_ProductId(productGroupId, productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("组合[%s]中不存在产品[%d]", productGroupId, productId)));

        // 获取关联产品
        Product product = Optional.ofNullable(item.getProduct())
                .orElseThrow(() -> new DataIntegrityViolationException(
                        "产品基础数据缺失，product_id: " + productId));

        // 计算并返回总值
        return product.getNetValue()
                .multiply(BigDecimal.valueOf(item.getAmount()))
                .setScale(2, RoundingMode.HALF_UP);
    }



    public Result<List<ProductItem>> getByGroupId(String groupId) {
        /*判断groupID是否存在*/
        if (!repository.existsByProductGroupId(groupId)) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return Result.build(repository.findByProductGroupId(groupId), ResultCodeEnum.SUCCESS);
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
}