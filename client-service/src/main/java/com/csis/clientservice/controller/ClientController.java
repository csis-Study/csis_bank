package com.csis.clientservice.controller;


import com.csis.clientservice.common.PageResult;
import com.csis.clientservice.common.Result;
import com.csis.clientservice.common.ResultCodeEnum;
import com.csis.clientservice.dto.BasicInfoDTO;
import com.csis.clientservice.exception.ResourceNotFoundException;
import com.csis.clientservice.pojo.Client;
import com.csis.clientservice.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@Validated
//@RequiredArgsConstructor // Lombok 自动生成包含所有 final 字段的构造器
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    // 新增客户（参数校验 + 统一响应）
    @PostMapping
    public Result<Client> createClient(@Valid @RequestBody Client client) {
        Client savedClient = clientService.addClient(client);
        return Result.build(savedClient, ResultCodeEnum.SUCCESS);
    }

    // 查询所有客户
    /*@GetMapping
    public Result<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return Result.build(clients, ResultCodeEnum.SUCCESS);
    }*/

    // 根据ID查询客户
    @GetMapping("/{usrId}")
    public Result<Client> getClientById(@PathVariable String usrId) {
        Client client = clientService.getClientById(usrId)
                .orElseThrow(() -> new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND));
        return Result.build(client, ResultCodeEnum.SUCCESS);
    }


    // 根据id删除客户
    @DeleteMapping("/{usrId}")
    public Result<Void> deleteClient(@PathVariable String usrId) {
        clientService.deleteClientByUsrId(usrId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //通过用户账户获取客户密码
    @GetMapping("/usrAccount/{usrAccount}")
    public Result<Map<String, String>> getClientCredentials(@PathVariable String usrAccount) {
        Map<String, String> credentials = clientService.getClientCredentials(usrAccount);
        return Result.build(credentials, ResultCodeEnum.SUCCESS);
    }


    //通过账户获取客户
    @GetMapping("/usrAccount1/{usrAccount}")
    public Result<Client> getClientByUsrAccount(@PathVariable String usrAccount) {
        Client client = clientService.getClientByUsrAccount(usrAccount);
        return Result.build(client, ResultCodeEnum.SUCCESS);
    }

    //通过账户进行删除客户
    @DeleteMapping("/usrAccount1/{usrAccount}")
    public Result<Void> deleteClientByUsrAccount(@PathVariable String usrAccount) {
        clientService.deleteClientByAccount(usrAccount);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    /**
     * 修改用户基本信息接口
     * @param usrAccount 用户ID（路径参数）
     * @param dto 请求体参数（JSON格式）
     * @return 统一响应格式，包含更新后的用户数据
     */
    @PutMapping("/{usrAccount}/basic-info")
    public Result<Client> updateBasicInfo(
            @PathVariable String usrAccount,
            @RequestBody @Valid BasicInfoDTO dto) {  // 启用参数校验
        Client updatedClient = clientService.updateBasicInfo(usrAccount, dto);
        return Result.build(updatedClient, ResultCodeEnum.SUCCESS);
    }
    /**
     * 修改用户总资产接口
     * @param usrAccount 用户ID（路径参数）
     * @param totalAssets 新的总资产值（请求参数，必须传递）
     * @return 统一响应格式
     */
    @PutMapping("/{usrAccount}/assets")
    public Result<Client> updateTotalAssets(
            @PathVariable String usrAccount,
            @RequestParam @NotNull(message = "资产金额不能为空") BigDecimal totalAssets) {
        Client updatedClient = clientService.updateTotalAssets(usrAccount, totalAssets);
        return Result.build(updatedClient, ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改风险等级和KYC时间接口
     * @param usrAccount 用户usrAccount（路径参数）
     * @param riskLevel 风险等级（可选，长度不超过20）
     * @param kycCheckDate 下次KYC日期（可选，必须为未来时间）
     * @return 统一响应格式
     */
    @PutMapping("/{usrAccount}/risk-kyc")
    public Result<Client> updateRiskAndKyc(
            @PathVariable String usrAccount,
            @RequestParam(required = false) @Size(max = 20) String riskLevel,
            @RequestParam(required = false) @Future(message = "KYC日期必须为未来时间") Date kycCheckDate) {
        Client updatedClient = clientService.updateRiskAndKyc(usrAccount, riskLevel, kycCheckDate);
        return Result.build(updatedClient, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据客户经理ID查询关联客户
     * @param managerId 必须符合格式：U+11位数字
     * @return 统一封装的响应结果*/
    /*@GetMapping("/manager/{managerId}")
    public Result<List<Client>> getClientsByManager(
            @PathVariable
            @Pattern(regexp = "^U\\d{11}$", message = "用户经理ID格式错误")  // 复用实体类校验规则
            String managerId
    ) {
        // 调用业务服务获取数据
        List<Client> clients = clientService.getClientsByManagerId(managerId);

        // 封装统一响应格式
        return Result.build(clients, ResultCodeEnum.SUCCESS);
    }*/

    /**
     * 分页查询所有客户
     * @param page 当前页码（默认1）
     * @param size 每页条数（默认10）
     */
    @GetMapping
    public Result<PageResult<Client>> getAllClients(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageResult<Client> result = clientService.getAllClients(page, size);
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据客户经理ID分页查询客户
     * @param managerId 客户经理ID（需符合U+11位数字格式）
     * @param page 当前页码（默认1）
     * @param size 每页条数（默认10）
     */
    @GetMapping("/manager/{managerId}")
    public Result<PageResult<Client>> getClientsByManager(
            @PathVariable
            @Pattern(regexp = "^U\\d{11}$", message = "用户经理ID格式错误")
            String managerId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageResult<Client> result = clientService.getClientsByManagerId(managerId, page, size);
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }

}