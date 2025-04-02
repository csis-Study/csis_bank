package com.csis.usrservice.clientfeign;



import com.csis.clientservice.pojo.Client;
import com.csis.usrservice.common.Result;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "client-service",path = "/clients")
public interface ClientFeign {
    /*根据usrId查询客户信息*/
    @GetMapping("/{usrId}")
    Result<Client> getClientById(@PathVariable String usrId);

    /*查询所有客户*/
    @GetMapping
    Result<List<Client>> getAllClients();

    /*删除客户*/
    @DeleteMapping("/{usrId}")
    Result<Void> deleteClient(@PathVariable String usrId);

    /*新增客户信息*/
    @PostMapping
    Result<Client> createClient(@Valid @RequestBody Client client);

    /*根据usrAccount查询客户密码*/
    @GetMapping("/usrAccount/{usrAccount}")
    Result<String> getClientPassword(@PathVariable String usrAccount);

}
