package com.csis.clientservice.service;


import com.csis.clientservice.dto.BasicInfoDTO;
import com.csis.clientservice.pojo.Client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClientService {
    Client addClient(Client client);
    void deleteClientByUsrId(String usrId);
    List<Client> getAllClients();
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
    List<Client> getClientsByManagerId(String relationshipManagerId);

    /*根据客户账户查询客户信息*/
    Client getClientByUsrAccount(String usrAccount);

    /*根据客户账户删除客户信息*/
    void deleteClientByAccount(String usrAccount);
}