package com.csis.authservice.feign;

import com.csis.authservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
@FeignClient(name = "usr-service")
public interface UserServiceFeign {
    // 三个方法对应不同角色
    @GetMapping("/advisors/{account}")
    Result<Map<String, String>> loadAdvisor(@PathVariable("account") String username);

    @GetMapping("/risk-officers/{account}")
    Result<Map<String, String>> loadRiskOfficer(@PathVariable("account") String username);

    @GetMapping("/approval-personnel/{account}")
    Result<Map<String, String>> loadComplianceOfficer(@PathVariable("account") String username);
    @GetMapping("/admins/{account}")
    Result<Map<String, String>> loadAdmin(@PathVariable("account") String username);
}