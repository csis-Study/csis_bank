package com.csis.usrservice.Service;

import com.csis.usrservice.pojo.Advisor;

import java.util.List;

public interface AdvisorService {

    // 增加客户经理
    Advisor addAdvisor(Advisor advisor);

    // 删除客户经理
    void deleteAdvisorById(String id);

    // 根据ID查询客户经理
    Advisor getAdvisorById(String id);


    // 查询所有客户经理
    List<Advisor> getAllAdvisors();

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

    // 根据账户 ，密码查询用户
    String login(String account);

}
