package com.example.portfolio.service;


import com.example.portfolio._enum.ProductStatus;
import com.example.portfolio._enum.ProductType;
import com.example.portfolio.entity.Product;
import com.example.portfolio.repository.ProductRepository;
import com.example.portfolio.util.Result;
import com.example.portfolio.util.ResultCodeEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Service
//@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // 每日刷新净值
    @Scheduled(cron = "0 0 0 * * *") // 每日零点执行
    @Transactional
    public void refreshDailyData() {
        List<Product> activeProducts = productRepository.findByStatus(ProductStatus.有效);
        Random random = new Random();

        activeProducts.forEach(product -> {
            double rate;

            // 生成-99到99的随机整数，转换为四位小数（如50 -> 50.0000）
            if(String.valueOf(product.getProductType()).equals("股票")){
                rate = random.nextDouble(199) - 99;
            }else if (String.valueOf(product.getProductType()).equals("基金")){
                rate = random.nextDouble(99) - 49;
            }else {
                rate = random.nextDouble(5);
            }

            BigDecimal floatRate = new BigDecimal(rate)
                    .setScale(4, RoundingMode.HALF_UP);

            // 计算新净值：旧净值 * (1 + floatRate/100)
            BigDecimal oldNetValue = product.getNetValue();
            BigDecimal rateFactor = floatRate.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
            BigDecimal newNetValue = oldNetValue.multiply(BigDecimal.ONE.add(rateFactor));

            // 更新字段
            product.setFloatRate(floatRate);
            product.setNetValue(newNetValue);
        });

        productRepository.saveAll(activeProducts);
    }


    // 新增产品
    public Result<Product> createProduct(Product product) {
        return Result.build(productRepository.save(product), ResultCodeEnum.SUCCESS);
    }

    // 更新产品
    public Result<Product> updateProduct(Product product) {
        return productRepository.findById(product.getProductId())
                .map(existing -> Result.build(productRepository.save(product), ResultCodeEnum.SUCCESS))
                .orElseGet(() -> Result.build(null, ResultCodeEnum.DATA_ERROR));
    }

    // 删除产品
    public Result<Void> deleteProduct(Integer productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    productRepository.delete(product);
                    return Result.build(null, ResultCodeEnum.SUCCESS);
                })
                .orElseGet(() -> Result.build(null, ResultCodeEnum.DATA_ERROR));
    }

    // 分页查询（类型）
    public Result<Page<Product>> getByType(ProductType type, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Result.build(productRepository.findByProductType(type, pageable), ResultCodeEnum.SUCCESS);
    }

    // 分页查询（名称模糊）
    public Result<Page<Product>> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Result.build(productRepository.findByProductNameContaining(name, pageable), ResultCodeEnum.SUCCESS);
    }
}