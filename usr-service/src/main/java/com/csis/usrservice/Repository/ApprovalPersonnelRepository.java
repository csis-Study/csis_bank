package com.csis.usrservice.Repository;

import com.csis.usrservice.pojo.ApprovalPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalPersonnelRepository extends JpaRepository<ApprovalPersonnel, String> {

    // 根据用户名查询审批人员
    ApprovalPersonnel findByUsername(String username);

    // 根据姓名模糊查询审批人员列表
    List<ApprovalPersonnel> findByNameContaining(String name);


    // 根据状态查询审批人员列表
    List<ApprovalPersonnel> findByStatus(String status);

    //根据账户名查询密码
    @Query("SELECT a.password FROM ApprovalPersonnel a WHERE a.username = ?1")
    String findPasswordByUsername(String username);

    // 自定义查询方法，查询最大的 approverId
    @Query("SELECT MAX(a.approverId) FROM ApprovalPersonnel a")
    String findMaxId();
}