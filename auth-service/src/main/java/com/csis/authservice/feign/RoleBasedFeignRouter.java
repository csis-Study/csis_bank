package com.csis.authservice.feign;

import com.csis.authservice.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 王昀
 */
@Component
public class RoleBasedFeignRouter {
    @Autowired
    private UserServiceFeign userServiceFeign;
    @Autowired
    private ClientServiceFeign clientServiceFeign;

    public Result<Map<String, String>> route(String role, String username) {
        if ("client".equals(role)){
            return clientServiceFeign.loadClient(username);
        }
        if ("advisor".equals(role)){
            return userServiceFeign.loadAdvisor(username);
        }
        if ("approval".equals(role)){
            return userServiceFeign.loadComplianceOfficer(username);
        }
        if ("admin".equals(role)){
            return userServiceFeign.loadAdmin(username);
        }
        if ("risk".equals(role)){
            return userServiceFeign.loadRiskOfficer(username);
        }
        return null;
    }
}