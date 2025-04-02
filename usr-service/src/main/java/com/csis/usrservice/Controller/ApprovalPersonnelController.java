package com.csis.usrservice.Controller;

import com.csis.usrservice.Service.ApprovalPersonnelService;
import com.csis.usrservice.common.Result;
import com.csis.usrservice.common.ResultCodeEnum;
import com.csis.usrservice.pojo.ApprovalPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/approval-personnel")
public class ApprovalPersonnelController {

    @Autowired
    private ApprovalPersonnelService approvalPersonnelService;

    /**
     * 添加审批人员
     * @param approvalPersonnel 审批人员对象
     * @return 添加后的审批人员对象
     */
    @PostMapping
    public Result<ApprovalPersonnel> addApprovalPersonnel(@RequestBody ApprovalPersonnel approvalPersonnel) {
        ApprovalPersonnel savedApprovalPersonnel = approvalPersonnelService.addApprovalPersonnel(approvalPersonnel);
        return Result.build(savedApprovalPersonnel,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除审批人员
     * @param id 审批人员ID
     * @return 删除成功的响应
     */
    @DeleteMapping("/{id}")
    public Result deleteApprovalPersonnelById(@PathVariable String id) {
        approvalPersonnelService.deleteApprovalPersonnelById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询所有审批人员
     * @return 审批人员列表
     */
    @GetMapping
    public Result<List<ApprovalPersonnel>> getAllApprovalPersonnel() {
        List<ApprovalPersonnel> approvalPersonnel = approvalPersonnelService.getAllApprovalPersonnel();
        return Result.build(approvalPersonnel,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据用户名查询审批人员
     * @param username 用户名
     * @return 审批人员对象
     */
    @GetMapping("/username/{username}")
    public Result<ApprovalPersonnel> findByUsername(@PathVariable String username) {
        ApprovalPersonnel approvalPersonnel = approvalPersonnelService.findByUsername(username);
        return Result.build(approvalPersonnel,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据姓名模糊查询审批人员列表
     * @param name 姓名
     * @return 审批人员列表
     */
    @GetMapping("/name/{name}")
    public Result<List<ApprovalPersonnel>> findByNameContaining(@PathVariable String name) {
        List<ApprovalPersonnel> approvalPersonnel = approvalPersonnelService.findByNameContaining(name);
        return Result.build(approvalPersonnel,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/{account}")
    public Result<Map<String,String>>  AccountFindPassword(@PathVariable String account){

        // 调用服务层的登录方法
        String encryptedPassword = approvalPersonnelService.login(account);

        if (encryptedPassword == null){
            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return Result.build(encryptedPassword,ResultCodeEnum.SUCCESS);

    }
}