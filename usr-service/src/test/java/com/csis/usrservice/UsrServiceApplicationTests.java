package com.csis.usrservice;

import com.csis.usrservice.Controller.AdminController;
import com.csis.usrservice.Repository.AdminRepository;
import com.csis.usrservice.Service.AdminService;
import com.csis.usrservice.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsrServiceApplicationTests {


	@Autowired
	private AdminService adminService;




	//添加管理员
	@Test
	void testAddAdmin() {
		Admin admin = new Admin();
		admin.setId("1");
		admin.setName("Test Admin");
		admin.setUsername("testuser");
		admin.setPassword("testpassword");
		admin.setPhoneNumber("19991648810");
		admin.setRole("admin");
		admin.setStatus(1);

		System.out.println(adminService.addAdmin(admin));


	}

	//查找所有用户
	@Test
	void testfindAdmin(){
		List<Admin> allAdmins = adminService.findAllAdmins();
		for(Admin admin : allAdmins){
			System.out.println(admin.getName());
		}
	}

	@Test
	void testfindAdminbyE_mile(){
		Optional<Admin> adminByEmail = adminService.findAdminByEmail("zhangsan@example.com");
		System.out.println(adminByEmail);
	}

}
