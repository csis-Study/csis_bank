package com.csis.authservice.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.csis.authservice.common.JwtResponse;
import com.csis.authservice.common.Result;
import com.csis.authservice.feign.RoleBasedFeignRouter;
import com.csis.authservice.service.AuthService;
import com.csis.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String KEY_PREFIX = "auth:";
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RoleBasedFeignRouter router;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Result<JwtResponse> login(String username, String password, String role) {
        Result<Map<String, String>> result = router.route(role, username);
        System.out.println("result: " + result);
        if (result.getCode() == 200){
            Map<String, String> resultMap = result.getData();
            System.out.println("resultMap: " + resultMap);
            String inPassword = MD5.create().digestHex(password);
            System.out.println("inPassword: " + inPassword);
            if (resultMap.get("password").equals(inPassword)){
                String token = jwtUtils.generateToken(username);
                /*redisTemplate.opsForValue().set(KEY_PREFIX + token,
                        String.valueOf(System.currentTimeMillis()),
                        1, TimeUnit.HOURS);*/
                HashMap<String, String> redisMap = new HashMap<>();
                redisMap.put("id", resultMap.get("id"));
                redisMap.put("issueTime", String.valueOf(System.currentTimeMillis()));
                redisTemplate.opsForHash().putAll(KEY_PREFIX + token, redisMap);
                return Result.success(new JwtResponse(token));
            }
            return Result.error("Invalid password");
        }
        return Result.error("Invalid username");

        /*if ("admin".equals(username) && "admin".equals(password)){
            System.out.println("用户名密码正确");
            String token = jwtUtils.generateToken("admin");
            redisTemplate.opsForValue().set(KEY_PREFIX + token,
                    String.valueOf(System.currentTimeMillis()),1, TimeUnit.HOURS);
            return Result.success(new JwtResponse(token));
        }
        return Result.error("Invalid username or password");*/
    }

    @Override
    public Result<String> logout(String token) {
        if (StrUtil.isBlank(token)){
            return Result.error("Invalid token");
        }
        String key = KEY_PREFIX + token;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))){
            return Result.error("Invalid token");
        }
        Boolean delete = redisTemplate.delete(key);
        return Boolean.TRUE.equals(delete) ? Result.success("Logout successfully") : Result.error("Logout failed");
    }
}
