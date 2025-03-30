package com.csis.usrservice.Repository;

import com.csis.usrservice.pojo.RiskOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskOfficerRepository extends JpaRepository<RiskOfficer, String> {

    // 根据用户名查询风险官员
    RiskOfficer findByUsername(String username);


    // 根据邮箱查询风险官员
    RiskOfficer findByEmail(String email);

    // 根据状态查询风险官员列表
    List<RiskOfficer> findByStatus(String status);

    // 根据姓名模糊查询风险官员列表
    List<RiskOfficer> findByNameContaining(String name);

    // 根据角色查询风险官员列表
    List<RiskOfficer> findByRole(String role);

    // 使用自定义查询方法
    @Query("SELECT r.password FROM RiskOfficer r WHERE r.username = ?1")
    String findPasswordByUsername(String username);

    // 自定义查询方法，查询最大的 riskOfficerId
    @Query("SELECT MAX(r.riskOfficerId) FROM RiskOfficer r")
    String findMaxId();
}