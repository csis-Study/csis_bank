package com.csis.authservice.service;

import com.csis.authservice.common.JwtResponse;
import com.csis.authservice.common.Result;

public interface AuthService {

    Result<JwtResponse> login(String username, String password, String role);

    Result<String> logout(String token);
}
