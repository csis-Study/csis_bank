package com.csis.gatewayservice;

import com.csis.gatewayservice.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayServiceApplicationTests {

    @Autowired
    private JwtUtils jwtUtils;
    @Test
    void contextLoads() {
        String token = jwtUtils.generateToken("admin");
        System.out.println(token);
        /*System.out.println(jwtUtils.validateToken(token1));
        System.out.println(jwtUtils.getUsernameFromToken(token1));*/
    }

}
