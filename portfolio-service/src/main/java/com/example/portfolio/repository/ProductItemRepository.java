package com.example.portfolio.repository;


import com.example.portfolio.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

    // 根据product_group_id分页查询
    Page<ProductItem> findByProductGroupIdContaining(String productGroupId, Pageable pageable);

    // 根据product_group_name分页查询
    Page<ProductItem> findByProductGroupNameContaining(String productGroupName, Pageable pageable);

    //根据product_group_id查询
    @Query("select p from ProductItem p where p.productGroupId = :groupId")
    List<ProductItem> findByProductGroupId(String groupId);

    boolean existsByProductGroupId(String groupId);
}