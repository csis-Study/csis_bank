package com.csis.authservice;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceApplicationTests {

	@Test
	void contextLoads() {
		long nexted = IdUtil.getSnowflake().nextId();
		System.out.println(nexted);
	}
}
