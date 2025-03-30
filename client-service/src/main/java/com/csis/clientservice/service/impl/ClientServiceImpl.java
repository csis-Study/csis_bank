package com.csis.clientservice.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.csis.clientservice.common.ResultCodeEnum;
import com.csis.clientservice.dto.BasicInfoDTO;
import com.csis.clientservice.exception.DuplicateAccountException;
import com.csis.clientservice.exception.ResourceNotFoundException;
import com.csis.clientservice.pojo.Client;
import com.csis.clientservice.repository.ClientRepository;
import com.csis.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service("ClientServiceImpl")
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client addClient(Client client) {
        // 检查账号唯一性
        if (clientRepository.existsByUsrAccount(client.getUsrAccount())) {
            throw new DuplicateAccountException("账号已存在");
        }

        //client.setUsrId(client.getIdNumber());
        // 密码加密（示例使用 MD5）
        //client.setUsrPasswd(MD5Util.md5(client.getUsrPasswd()));
        client.setUsrPasswd(MD5.create().digestHex16(client.getUsrPasswd()));
        // 保存到数据库（关键操作）
        return clientRepository.save(client);
    }

    @Override
    public void deleteClientByUsrId(String usrId) {
        // 校验存在性
        if (!clientRepository.existsById(usrId)) {
            throw new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        clientRepository.deleteById(usrId);
    }


    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    @Override
    public Optional<Client> getClientById(String usrId) {
        return Optional.ofNullable(clientRepository.findByUsrId(usrId));
    }

    /*使用账户得到密码和usrId*/
    @Override
    public Map<String, String> getClientCredentials(String usrAccount) {
        Client client = clientRepository.findByUsrAccount(usrAccount)
                .orElseThrow(() -> new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND));

        Map<String, String> credentials = new HashMap<>();
        credentials.put("id", client.getUsrId());
        credentials.put("password", client.getUsrPasswd());
        return credentials;
    }

    /**
     * 修改用户基本信息
     * @param usrAccount 用户ID
     * @param dto 修改的字段封装
     * @return 更新后的用户对象
     */
    @Override
    @Transactional
    public Client updateBasicInfo(String usrAccount, BasicInfoDTO dto) {
        // 1. 查询用户是否存在
        Client client = clientRepository.findByUsrAccount(usrAccount)
                .orElseThrow(() -> new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND));

        // 2. 更新非空字段
        if (dto.getName() != null) client.setName(dto.getName());
        if (dto.getGender() != null) client.setGender(dto.getGender());
        if (dto.getPhoneNumber() != null) client.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getNationality() != null) client.setNationality(dto.getNationality());
        if (dto.getIncomeLevel() != null) client.setIncomeLevel(dto.getIncomeLevel());
        if (dto.getStatus() != null) client.setStatus(dto.getStatus());
        if (dto.getRemarks() != null) client.setRemarks(dto.getRemarks());

        // 3. 保存并返回更新后的对象
        return clientRepository.save(client);
    }


     /**
 * 修改用户总资产并且
 * @param usrAccount 用户ID
 * @param totalAssets 新的总资产值（必须非空）
 * @re*/

    @Override
    @Transactional
    public Client updateTotalAssets(String usrAccount, BigDecimal totalAssets) {
        Client client = clientRepository.findByUsrAccount(usrAccount)
                .orElseThrow(() -> new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND));
        client.setTotalAssets(totalAssets);
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateRiskAndKyc(String usrAccount, String riskLevel, Date kycCheckDate) {
        // 1. 查询用户是否存在
        Client client = clientRepository.findByUsrAccount(usrAccount)
                .orElseThrow(() -> new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND));

        // 2. 更新字段（允许部分更新）
        if (riskLevel != null) client.setRiskLevel(riskLevel);
        if (kycCheckDate != null) {
            // 业务逻辑校验：KYC日期必须为未来时间（由Controller层注解校验）
            client.setKycCheckDate(kycCheckDate);
        }

        // 3. 保存并返回
        return clientRepository.save(client);
    }


    @Override
    public List<Client> getClientsByManagerId(String relationshipManagerId) {
        // 执行数据库查询（JPA会自动使用@Entity校验参数格式）
        List<Client> clients = clientRepository.findByRelationshipManagerId(relationshipManagerId);

        // 业务逻辑校验：查询结果为空视为资源不存在
        if (clients.isEmpty()) {
            //log.warn("未找到客户经理[{}]关联的客户", relationshipManagerId);
            throw new ResourceNotFoundException(ResultCodeEnum.NOT_FOUND);
        }

        //log.info("成功获取客户经理[{}]的{}个客户", relationshipManagerId, clients.size());
        return clients;
    }

    @Override
    public Client getClientByUsrAccount(String usrAccount) {
        /*校验客户是否存在*/
        if (!clientRepository.existsByUsrAccount(usrAccount)) {
            throw new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return clientRepository.findByUsrAccount(usrAccount).orElse(null);

    }

    @Transactional
    @Override
    public void deleteClientByAccount(String usrAccount) {
        /*校验用户是否存在*/
        if (!clientRepository.existsByUsrAccount(usrAccount)) {
            throw new ResourceNotFoundException(ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        clientRepository.deleteByUsrAccount(usrAccount);
    }



}