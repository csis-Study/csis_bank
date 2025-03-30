package com.csis.usrservice.Service.Impl;

import cn.hutool.crypto.digest.MD5;
import com.csis.usrservice.Service.ApprovalPersonnelService;
import com.csis.usrservice.pojo.ApprovalPersonnel;
import com.csis.usrservice.Repository.ApprovalPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalPersonnelServiceImpl implements ApprovalPersonnelService {

    @Autowired
    private ApprovalPersonnelRepository approvalPersonnelRepository;

    @Override
    public ApprovalPersonnel addApprovalPersonnel(ApprovalPersonnel approvalPersonnel) {
        // 在添加审批人员之前，可以添加一些业务逻辑，比如验证必填字段等
        if (approvalPersonnel.getName() == null || approvalPersonnel.getUsername() == null || approvalPersonnel.getEmail() == null) {
            throw new IllegalArgumentException("Name, username, and email are required fields");
        }

        // 获取当前数据库中已有的最大 approverId
        String maxId = approvalPersonnelRepository.findMaxId();
        long nextId;

        if (maxId == null || maxId.isEmpty()) {
            // 如果数据库为空，从 U200000000001 开始
            nextId = 1;
        } else {
            try {
                // 去掉前缀 "U2"，并解析为数字
                nextId = Long.parseLong(maxId.substring(2)) + 1;
            } catch (NumberFormatException e) {
                // 如果解析失败，记录错误日志并抛出异常
                throw new IllegalArgumentException("Invalid approverId format: " + maxId, e);
            }
        }

        // 生成新的用户 ID，格式为 U2 + 10 位数字
        String newId = String.format("U2%010d", nextId);

        // 为 approvalPersonnel 设置新的 approverId
        approvalPersonnel.setApproverId(newId);
        approvalPersonnel.setRole("approver");
        String password = approvalPersonnel.getPassword();
        String MD5pwd = MD5.create().digestHex(password);

        approvalPersonnel.setPassword(MD5pwd);
        // 保存到数据库
        return approvalPersonnelRepository.save(approvalPersonnel);
    }

    @Override
    public void deleteApprovalPersonnelById(String id) {
        approvalPersonnelRepository.deleteById(id);
    }

    @Override
    public List<ApprovalPersonnel> getAllApprovalPersonnel() {
        return approvalPersonnelRepository.findAll();
    }

    @Override
    public ApprovalPersonnel findByUsername(String username) {
        return approvalPersonnelRepository.findByUsername(username);
    }

    @Override
    public List<ApprovalPersonnel> findByNameContaining(String name) {
        return approvalPersonnelRepository.findByNameContaining(name);
    }

    @Override
    public String login(String account) {
        return approvalPersonnelRepository.findPasswordByUsername(account);
    }
}