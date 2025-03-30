package com.csis.usrservice.Service.Impl;

import cn.hutool.crypto.digest.MD5;
import com.csis.usrservice.Service.AdvisorService;
import com.csis.usrservice.pojo.Advisor;
import com.csis.usrservice.Repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class AdvisorServiceImpl implements AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    @Override
    public Advisor addAdvisor(Advisor advisor) {
        // 在添加客户经理之前，可以添加一些业务逻辑，比如验证必填字段等
        if (advisor.getName() == null || advisor.getPhoneNumber() == null || advisor.getEmail() == null) {
            throw new IllegalArgumentException("Name, phoneNumber, and email are required fields");
        }
        String maxId = advisorRepository.findMaxId(); // 假设你有一个方法可以查询最大用户 ID
        long nextId;

        if (maxId == null || maxId.isEmpty()) {
            // 如果数据库为空，从 U000000000001 开始
            nextId = 1;
        } else {
            // 去掉前缀 "U"，并解析为数字
            nextId = Long.parseLong(maxId.substring(1)) + 1;
        }

        // 生成新的用户 ID，格式为 类型标识 + 11 位数字
        String newId = String.format("U1%010d", nextId);

//        // 生成新的用户 ID，格式为 U + 12 位数字
//        String newId = String.format("U%012d", nextId);

        // 设置新的用户 ID
        advisor.setAdvisorId(newId);
        String password = advisor.getPassword();
        String md5password = MD5.create().digestHex(password);
        advisor.setStatus(1);
        advisor.setRole("advisor");
        advisor.setPassword(md5password);
        return advisorRepository.save(advisor);
    }

    @Override
    public void deleteAdvisorById(String id) {
        advisorRepository.deleteById(id);
    }

    @Override
    public Advisor getAdvisorById(String id) {
        Optional<Advisor> advisor = advisorRepository.findById(id);
        if (advisor.isPresent()) {
            return advisor.get();
        } else {
            throw new RuntimeException("Advisor not found with id: " + id);
        }
    }



    @Override
    public List<Advisor> getAllAdvisors() {
        return advisorRepository.findAll();
    }

    @Override
    public Advisor findByPhoneNumber(String phoneNumber) {
        return advisorRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Advisor findByEmail(String email) {
        return advisorRepository.findByEmail(email);
    }

    @Override
    public Advisor findByAccount(String account) {
        return advisorRepository.findByAccount(account);
    }

    @Override
    public List<Advisor> findByStatus(Integer status) {
        return advisorRepository.findByStatus(status);
    }

    @Override
    public List<Advisor> findByNameContaining(String name) {
        return advisorRepository.findByNameContaining(name);
    }

    @Override
    public Advisor findByIdNumber(String idNumber) {
        return advisorRepository.findByIdNumber(idNumber);
    }

    @Override
    public String login(String account) {
        return advisorRepository.findByAccounttoPassword(account);
    }
}