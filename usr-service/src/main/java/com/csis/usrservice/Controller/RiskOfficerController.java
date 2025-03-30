package com.csis.usrservice.Controller;

import com.csis.usrservice.Service.RiskOfficerService;
import com.csis.usrservice.common.Result;
import com.csis.usrservice.common.ResultCodeEnum;
import com.csis.usrservice.pojo.RiskOfficer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/risk-officers")
public class RiskOfficerController {

    @Autowired
    private RiskOfficerService riskOfficerService;

    /**
     * 添加风险官员
     * @param riskOfficer 风险官员对象
     * @return 添加后的风险官员对象
     */
    @PostMapping
    public Result<RiskOfficer> addRiskOfficer(@RequestBody RiskOfficer riskOfficer) {
        RiskOfficer savedRiskOfficer = riskOfficerService.addRiskOfficer(riskOfficer);
        return Result.build(savedRiskOfficer,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除风险官员
     * @param id 风险官员ID
     * @return 删除成功的响应
     */
    @DeleteMapping("/{id}")
    public Result deleteRiskOfficerById(@PathVariable String id) {
        riskOfficerService.deleteRiskOfficerById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询所有风险官员
     * @return 风险官员列表
     */
    @GetMapping
    public Result<List<RiskOfficer>> getAllRiskOfficers() {
        List<RiskOfficer> riskOfficers = riskOfficerService.getAllRiskOfficers();
        return Result.build(riskOfficers,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据用户名查询风险官员
     * @param username 用户名
     * @return 风险官员对象
     */
    @GetMapping("/username/{username}")
    public Result<RiskOfficer> findByUsername(@PathVariable String username) {
        RiskOfficer riskOfficer = riskOfficerService.findByUsername(username);
        return Result.build(riskOfficer,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据状态查询风险官员列表
     * @param status 状态
     * @return 风险官员列表
     */
    @GetMapping("/status/{status}")
    public Result<List<RiskOfficer>> findByStatus(@PathVariable String status) {
        List<RiskOfficer> riskOfficers = riskOfficerService.findByStatus(status);
        return Result.build(riskOfficers,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据姓名模糊查询风险官员列表
     * @param name 姓名
     * @return 风险官员列表
     */
    @GetMapping("/name/{name}")
    public Result<List<RiskOfficer>> findByNameContaining(@PathVariable String name) {
        List<RiskOfficer> riskOfficers = riskOfficerService.findByNameContaining(name);
        return Result.build(riskOfficers,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/{account}")
    public Result  AccountFindPassword(@PathVariable String account){

        // 调用服务层的登录方法
        String encryptedPassword = riskOfficerService.login(account);

        if (encryptedPassword == null){
            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return Result.build(encryptedPassword,ResultCodeEnum.SUCCESS);

    }
}