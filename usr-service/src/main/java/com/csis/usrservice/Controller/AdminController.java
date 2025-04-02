package com.csis.usrservice.Controller;

import com.csis.usrservice.Service.AdminService;
import com.csis.usrservice.common.Result;
import com.csis.usrservice.common.ResultCodeEnum;
import com.csis.usrservice.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 杜浩杰
 * @version 1.0
 * 2025/3/25
 */


@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService ;


    // 查询所有管理员
    @GetMapping
    public Result<List<Admin>> findAllAdmins() {
        List<Admin> admins = adminService.findAllAdmins();
        return Result.build(admins, ResultCodeEnum.SUCCESS);
    }

    // 根据用户名查询管理员
    @GetMapping("/username/{username}")
    public Result<Admin> findAdminByUsername(@PathVariable String username) {
        Optional<Admin> admin = adminService.findAdminByUsername(username);
        return Result.build(admin,ResultCodeEnum.SUCCESS);
    }


    // 添加管理员
    @PostMapping
    public Result<Admin> addAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.addAdmin(admin);
        return  Result.build(admin,ResultCodeEnum.SUCCESS);
//                ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }


    // 删除管理员
    @DeleteMapping("/{id}")
    public Result deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //根据姓名模糊查询
    @GetMapping("/name/{name}")
    public Result<List<Admin>> findAdminsByNameContaining(@PathVariable String name) {
        List<Admin> admins = adminService.findAdminsByNameContaining(name);
        return Result.build(admins,ResultCodeEnum.SUCCESS);
    }

    //
    @GetMapping("/{account}")
    public Result<Map<String,String>>  AccountFindPassword(@PathVariable String account) {

        // 调用服务层的登录方法
//        String encryptedPassword = adminService.login(account);
//
//        if (encryptedPassword == null){
//            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
//        }
//        return Result.build(encryptedPassword,ResultCodeEnum.SUCCESS);
//
//    }
        return null;
    }


}
