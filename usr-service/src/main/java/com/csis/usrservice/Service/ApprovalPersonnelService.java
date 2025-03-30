package com.csis.usrservice.Service;

import com.csis.usrservice.pojo.ApprovalPersonnel;
import java.util.List;

public interface ApprovalPersonnelService {

    // 增加审批人员
    ApprovalPersonnel addApprovalPersonnel(ApprovalPersonnel approvalPersonnel);

    // 删除审批人员
    void deleteApprovalPersonnelById(String id);


    // 查询所有审批人员
    List<ApprovalPersonnel> getAllApprovalPersonnel();

    // 根据用户名查询审批人员
    ApprovalPersonnel findByUsername(String username);

    // 根据姓名模糊查询审批人员列表
    List<ApprovalPersonnel> findByNameContaining(String name);

    // 根据账户 ，密码查询用户
    String login(String account);
}