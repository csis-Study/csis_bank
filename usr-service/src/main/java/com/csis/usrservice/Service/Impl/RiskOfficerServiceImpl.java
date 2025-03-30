package com.csis.usrservice.Service.Impl;

import cn.hutool.crypto.digest.MD5;
import com.csis.usrservice.Service.RiskOfficerService;
import com.csis.usrservice.pojo.RiskOfficer;
import com.csis.usrservice.Repository.RiskOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiskOfficerServiceImpl implements RiskOfficerService {

    @Autowired
    private RiskOfficerRepository riskOfficerRepository;

    @Override
    public RiskOfficer addRiskOfficer(RiskOfficer riskOfficer) {
        // 在添加风险官员之前，可以添加一些业务逻辑，比如验证必填字段等
        if (riskOfficer.getName() == null || riskOfficer.getUsername() == null || riskOfficer.getEmail() == null) {
            throw new IllegalArgumentException("Name, username, and email are required fields");
        }

        String maxId = riskOfficerRepository.findMaxId(); // 假设你有一个方法可以查询最大用户 ID
        long nextId;

        if (maxId == null || maxId.isEmpty()) {
            // 如果数据库为空，从 U000000000001 开始
            nextId = 1;
        } else {
            // 去掉前缀 "U"，并解析为数字
            nextId = Long.parseLong(maxId.substring(1)) + 1;
        }

        // 生成新的用户 ID，格式为 类型标识 + 11 位数字
        String newId = String.format("U3%010d", nextId);

        String password = riskOfficer.getPassword();
        String md5pwd = MD5.create().digestHex16(password);
        System.out.println(newId);
        riskOfficer.setRiskOfficerId(newId);
        riskOfficer.setPassword(md5pwd);
        riskOfficer.setStatus("1");
        System.out.println(riskOfficer);
        return riskOfficerRepository.save(riskOfficer);
    }

    @Override
    public void deleteRiskOfficerById(String id) {
        riskOfficerRepository.deleteById(id);
    }




    @Override
    public List<RiskOfficer> getAllRiskOfficers() {
        return riskOfficerRepository.findAll();
    }

    @Override
    public RiskOfficer findByUsername(String username) {
        return riskOfficerRepository.findByUsername(username);
    }

    @Override
    public List<RiskOfficer> findByStatus(String status) {
        return riskOfficerRepository.findByStatus(status);
    }

    @Override
    public List<RiskOfficer> findByNameContaining(String name) {
        return riskOfficerRepository.findByNameContaining(name);
    }

    @Override
    public String login(String account) {
        return riskOfficerRepository.findPasswordByUsername(account);
    }
}