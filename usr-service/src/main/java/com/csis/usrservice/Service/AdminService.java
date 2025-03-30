package com.csis.usrservice.Service;

import com.csis.usrservice.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/25
 */


public interface AdminService {

    // 查询所有管理员
    List<Admin> findAllAdmins();

    // 根据ID查询管理员
    Optional<Admin> findAdminById(String id);

    // 根据用户名查询管理员
    Optional<Admin> findAdminByUsername(String username);

    // 根据手机号查询管理员
    Optional<Admin> findAdminByPhoneNumber(String phoneNumber);

    // 根据邮箱查询管理员
    Optional<Admin> findAdminByEmail(String email);

    // 添加管理员
    Admin addAdmin(Admin admin);

    // 更新管理员信息
    Admin updateAdmin(String id, Admin adminDetails);

    // 删除管理员
    void deleteAdmin(String id);

    // 根据状态查询管理员列表
    List<Admin> findAdminsByStatus(Integer status);

    // 根据姓名模糊查询管理员列表
    List<Admin> findAdminsByNameContaining(String name);

    // 根据账户 ，密码查询用户
    String login(String account);
}
