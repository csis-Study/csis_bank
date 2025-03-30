package com.csis.clientservice.feign;

import com.example.portfolio.entity.ProductItem;
import com.example.portfolio.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "portfolio-service",path = "/product-items")
public interface PortfolioOpenFeign {

    @GetMapping("/{groupId}")
    public Result<List<ProductItem>> getByGroupId(@PathVariable String groupId);

}
