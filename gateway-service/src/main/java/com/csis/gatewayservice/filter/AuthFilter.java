package com.csis.gatewayservice.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.PathMatcher;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String KEY_PREFIX = "auth:";
    private static final Long MAX_EXPIRE_TIME = 60 * 60 * 8L * 1000;
    private final StringRedisTemplate redisTemplate;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private static final List<String> WHITE_LIST = List.of(
            "/api/auth/login",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("请求进入网关 path: " + exchange.getRequest().getURI().getPath());
        ServerHttpRequest request = exchange.getRequest();
        boolean isWhiteListed = WHITE_LIST.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, request.getURI().getPath()));
        //放行登录接口
        if (isWhiteListed){
            System.out.println("放行白名单");
            return chain.filter(exchange);
        }
        //获取请求头中的token
        String token = request.getHeaders().getFirst("Authorization");
        System.out.println("token" + token);
        if (token == null){
            //无token 返回未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //从redis判断token是否存在
        if (Boolean.FALSE.equals(redisTemplate.hasKey(KEY_PREFIX + token))){
            System.out.println("redis中没有此token");
            //token不合法或已过期
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //判断token是否过期
        String issueTime = Objects.requireNonNull(redisTemplate.opsForHash().get(KEY_PREFIX + token,
                "issueTime")).toString();
        System.out.println("issueTime" + issueTime);
        if (Long.parseLong(Objects.requireNonNull(issueTime))
        + MAX_EXPIRE_TIME < System.currentTimeMillis()){
            System.out.println("token过期");
            //token过期 返回未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //合法token 且未过期
        System.out.println("合法token");
        String id = String.valueOf(redisTemplate.opsForHash().get(KEY_PREFIX + token, "id"));
        //将用户id放入请求头中
        ServerHttpRequest httpRequest = request.mutate().header("X-user-id", id).build();
        return chain.filter(exchange.mutate().request(httpRequest).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
