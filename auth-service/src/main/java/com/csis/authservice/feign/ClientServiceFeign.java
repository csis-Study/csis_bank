package com.csis.authservice.feign;

import com.csis.authservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
@FeignClient(name = "client-service", path = "/clients")
public interface ClientServiceFeign {
    @GetMapping("/usrAccount/{usrAccount}")
    Result<Map<String, String>> loadClient(@PathVariable("usrAccount") String username);
}