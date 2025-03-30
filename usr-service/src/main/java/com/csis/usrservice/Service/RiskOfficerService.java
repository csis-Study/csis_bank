package com.csis.usrservice.Service;

import com.csis.usrservice.pojo.RiskOfficer;

import java.util.List;

public interface RiskOfficerService {

    // 增加风险官员
    RiskOfficer addRiskOfficer(RiskOfficer riskOfficer);

    // 删除风险官员
    void deleteRiskOfficerById(String id);


    // 查询所有风险官员
    List<RiskOfficer> getAllRiskOfficers();

    // 根据用户名查询风险官员
    RiskOfficer findByUsername(String username);



    // 根据状态查询风险官员列表
    List<RiskOfficer> findByStatus(String status);

    // 根据姓名模糊查询风险官员列表
    List<RiskOfficer> findByNameContaining(String name);

    // 根据账户 ，密码查询用户
    String login(String account);
}