package com.csis.authservice.controller;

import com.csis.authservice.common.JwtResponse;
import com.csis.authservice.common.LoginRequest;
import com.csis.authservice.common.Result;
import com.csis.authservice.feign.RoleBasedFeignRouter;
import com.csis.authservice.service.AuthService;
import com.csis.authservice.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result<JwtResponse> login(@RequestBody LoginRequest request) {
        System.out.println("请求到底login接口");
        return authService.login(request.getUsername(), request.getPassword(), request.getRole());
    }
    @PostMapping("/logout")
    public Result<String> logout(@RequestHeader("Authorization") String token) {
        System.out.println("请求到底logout接口");
        return authService.logout(token);
    }
}