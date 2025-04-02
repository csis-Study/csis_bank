package com.csis.usrservice.Repository;

import com.csis.usrservice.pojo.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, String> {

    // 根据手机号查询客户经理
    Advisor findByPhoneNumber(String phoneNumber);

    // 根据邮箱查询客户经理
    Advisor findByEmail(String email);

    // 根据账号查询客户经理
    Advisor findByAccount(String account);

    // 根据状态查询客户经理列表
    List<Advisor> findByStatus(Integer status);

    // 根据姓名模糊查询客户经理列表
    List<Advisor> findByNameContaining(String name);

    // 根据证件号码查询客户经理
    Advisor findByIdNumber(String idNumber);

    // 根据账户名查询密码
    @Query("SELECT a.password FROM Advisor a WHERE a.account = ?1")
    String findByAccounttoPassword(String account);

    List<Advisor> findAll();

    // 自定义查询方法，查询最大的 advisorId
    @Query("SELECT MAX(a.advisorId) FROM Advisor a")
    String findMaxId();
}