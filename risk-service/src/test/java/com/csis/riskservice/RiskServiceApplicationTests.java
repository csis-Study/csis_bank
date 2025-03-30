package com.csis.riskservice;

import com.csis.riskservice.pojo.testb;
import com.csis.riskservice.pojo.testc;
import com.csis.riskservice.pojo.testd;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RiskServiceApplicationTests {

	@Test
	void contextLoads() {
		testb test = new testb();
		testc test2 = new testc();
		testd test3 = new testd();
		System.out.println(test.testa(1,2));
		System.out.println(test2.testa(4,1));
		System.out.println(test3.testa(4,2));
		test.system();
		System.out.println(test2.a);
	}

}
