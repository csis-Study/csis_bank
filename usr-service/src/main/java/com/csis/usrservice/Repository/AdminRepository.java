package com.csis.usrservice.Repository;
import com.csis.usrservice.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    // 根据用户名查询管理员
    Admin findByUsername(String username);

    // 根据手机号查询管理员
    Admin findByPhoneNumber(String phoneNumber);

    // 根据邮箱查询管理员
    Admin findByEmail(String email);

    // 根据状态查询管理员列表
    List<Admin> findByStatus(Integer status);

    // 根据姓名模糊查询管理员列表
    List<Admin> findByNameContaining(String name);

    // 根据角色查询管理员列表
    List<Admin> findByRole(String role);


    // 根据账户名查询密码
    // 使用自定义查询方法
    @Query("SELECT a.id, a.password FROM Admin a WHERE a.username = ?1")
    String findPasswordByUsername(String username);


    // 自定义查询方法
    @Query("SELECT MAX(a.id) FROM Admin a")
    String findMaxId();
}