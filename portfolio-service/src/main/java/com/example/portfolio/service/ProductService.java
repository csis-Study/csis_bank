package com.example.portfolio.service;

import com.example.portfolio._enum.ProductType;
import com.example.portfolio.entity.Product;
import com.example.portfolio.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 创建产品
    public Product createProduct(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    // 更新产品
    public Product updateProduct(String productId, Product product) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        BeanUtils.copyProperties(product, existing, "productId");
        return productRepository.save(existing);
    }

    // 删除产品
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    // 按类型查询
    public List<Product> getProductsByType(ProductType type) {
        return productRepository.findByProductType(type);
    }

    // 按名称模糊查询
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }
}