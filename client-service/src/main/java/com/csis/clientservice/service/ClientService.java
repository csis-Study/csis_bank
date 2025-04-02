package com.csis.clientservice.service;



import com.csis.clientservice.common.PageResult;
import com.csis.clientservice.dto.BasicInfoDTO;
import com.csis.clientservice.pojo.Client;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface ClientService {
    Client addClient(Client client);
    void deleteClientByUsrId(String usrId);
    //List<Client> getAllClients();
    Optional<Client> getClientById(String usrId);
    Map<String, String> getClientCredentials(String usrAccount);


    /**
     * 修改用户基本信息
     * @param usrAccount 用户ID
     * @param dto 修改的字段封装
     * @return 更新后的用户对象
     */
    Client updateBasicInfo(String usrAccount, BasicInfoDTO dto);

    /**
     * 修改用户总资产
     * @param usrAccount 用户ID
     * @param totalAssets 新的总资产值（必须非空）
     * @return 更新后的用户对象
     */
    Client updateTotalAssets(String usrAccount, BigDecimal totalAssets);
    /**
     * 修改用户风险等级和KYC时间
     * @param usrAccount 用户usrAccount
     * @param riskLevel 风险等级（可选）
     * @param kycCheckDate 下次KYC日期（可选，必须为未来时间）
     * @return 更新后的用户对象
     */
    Client updateRiskAndKyc(String usrAccount, String riskLevel, Date kycCheckDate);

    /*通过经理id查询名下的客户*/
    //List<Client> getClientsByManagerId(String relationshipManagerId);

    /*根据客户账户查询客户信息*/
    Client getClientByUsrAccount(String usrAccount);

    /*根据客户账户删除客户信息*/
    void deleteClientByAccount(String usrAccount);

    /*分页*/
    /**
     * 分页查询所有客户
     * @param page 当前页码（从1开始）
     * @param size 每页条数
     * @return 分页结果对象
     */
    PageResult<Client> getAllClients(int page, int size);

    /**
     * 根据客户经理ID分页查询客户
     * @param managerId 客户经理ID（需符合U+11位数字格式）
     * @param page 当前页码（从1开始）
     * @param size 每页条数
     * @return 分页结果对象
     */
    PageResult<Client> getClientsByManagerId(String managerId, int page, int size);
}