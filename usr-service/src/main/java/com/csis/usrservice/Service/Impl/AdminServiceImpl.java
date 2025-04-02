package com.csis.usrservice.Service.Impl;

import cn.hutool.crypto.digest.MD5;
import com.csis.usrservice.Repository.AdminRepository;
import com.csis.usrservice.Service.AdminService;
import com.csis.usrservice.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 *
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/25
 */
@Service
public class  AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findAdminById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> findAdminByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        return Optional.ofNullable(admin);
    }

    @Override
    public Optional<Admin> findAdminByPhoneNumber(String phoneNumber) {
        Admin admin = adminRepository.findByPhoneNumber(phoneNumber);
        return Optional.ofNullable(admin);
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        return Optional.ofNullable(admin);
    }

    @Override
    public Admin addAdmin(Admin admin) {

        String maxId = adminRepository.findMaxId(); // 假设你有一个方法可以查询最大用户 ID
        long nextId;

        if (maxId == null || maxId.isEmpty()) {
            // 如果数据库为空，从 U000000000001 开始
            nextId = 1;
        } else {
            // 去掉前缀 "U"，并解析为数字
            nextId = Long.parseLong(maxId.substring(1)) + 1;
            System.out.println(nextId); //---->6
        }

        // 生成新的用户 ID，格式为 类型标识 + 11 位数字
        String newId = String.format("U0%010d", nextId);
        System.out.println(newId);

//        // 生成新的用户 ID，格式为 U + 12 位数字
//        String newId = String.format("U%012d", nextId);

        // 设置新的用户 ID
        admin.setId(newId);

        // 对密码进行 MD5 加密
        String password = admin.getPassword();
        String md5password = MD5.create().digestHex(password);
        admin.setRole("admin");
        admin.setPassword(md5password);
        admin.setStatus(1);
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(String id, Admin adminDetails) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            Admin updatedAdmin = admin.get();
            updatedAdmin.setName(adminDetails.getName());
            updatedAdmin.setGender(adminDetails.getGender());
            updatedAdmin.setBirthday(adminDetails.getBirthday());
            updatedAdmin.setPhoneNumber(adminDetails.getPhoneNumber());
            updatedAdmin.setEmail(adminDetails.getEmail());
            updatedAdmin.setNationality(adminDetails.getNationality());
            updatedAdmin.setIdType(adminDetails.getIdType());
            updatedAdmin.setIdNumber(adminDetails.getIdNumber());
            updatedAdmin.setUsername(adminDetails.getUsername());
            updatedAdmin.setPassword(adminDetails.getPassword());
            updatedAdmin.setStatus(adminDetails.getStatus());
            updatedAdmin.setRole(adminDetails.getRole());
            return adminRepository.save(updatedAdmin);
        }
        return null;
    }

    @Override
    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> findAdminsByStatus(Integer status) {
        return adminRepository.findByStatus(status);
    }

    @Override
    public List<Admin> findAdminsByNameContaining(String name) {
        return adminRepository.findByNameContaining(name);
    }

    @Override
    public Map<String,String> login(String account) {
//        return
        String passwordByUsername = adminRepository.findPasswordByUsername(account);
            return  null;
    }
}
