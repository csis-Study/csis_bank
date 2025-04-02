package com.example.portfolio.repository;


import com.example.portfolio.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

    // 根据product_group_id分页查询
    Page<ProductItem> findByProductGroupIdContaining(String productGroupId, Pageable pageable);

    // 根据product_group_name分页查询
    Page<ProductItem> findByProductGroupNameContaining(String productGroupName, Pageable pageable);

    // 根据组合ID查询所有产品项
    List<ProductItem> findByProductGroupId(String productGroupId);

    // 根据组合ID查询组合内产品ID，根据产品ID查询某个产品
    Optional<ProductItem> findByProductGroupIdAndProduct_ProductId(String productGroupId, Integer productId);

    boolean existsByProductGroupId(String groupId);


}