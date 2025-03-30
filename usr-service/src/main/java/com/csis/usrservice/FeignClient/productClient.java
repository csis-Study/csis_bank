package com.csis.usrservice.FeignClient;


import com.csis.usrservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="portfolio-service")
public interface productClient {

    @DeleteMapping("/api/products/{id}")
    Result<Void> delete(@PathVariable Integer id);
}
