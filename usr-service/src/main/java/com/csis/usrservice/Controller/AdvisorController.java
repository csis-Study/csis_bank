package com.csis.usrservice.Controller;
import com.csis.clientservice.pojo.Client;
import com.csis.usrservice.FeignClient.clientClient;
import com.csis.usrservice.FeignClient.productClient;
import com.csis.usrservice.Service.AdvisorService;
import com.csis.usrservice.clientfeign.ClientFeign;
import com.csis.usrservice.common.Result;
import com.csis.usrservice.common.ResultCodeEnum;
import com.csis.usrservice.pojo.Advisor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/advisors")

public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private clientClient client;

    @Autowired
    private productClient  product;


    @Autowired
    private ClientFeign clientFeign;

    /*根据usrId查询客户信息*/
    @GetMapping("/clients/{usrId}")
    public Result<Client> getClientById(@PathVariable String usrId) {
        Client client = clientFeign.getClientById(usrId).getData();
        return Result.build(client, ResultCodeEnum.SUCCESS);
    }

    /*查询所有用户*/
    @GetMapping("/clients")
    public Result<List<Client>> getAllClients() {
        List<Client> clients = clientFeign.getAllClients().getData();
        return Result.build(clients, ResultCodeEnum.SUCCESS);
    }

    /*
    查询所有客户经理
     */
    @GetMapping
    public Result<List<Advisor>> getAllAdvisor(){
        List<Advisor> allAdvisors = advisorService.getAllAdvisors();

        return Result.build(allAdvisors,ResultCodeEnum.SUCCESS);
    }

    /*根据usrId删除客户*/
    @DeleteMapping("/clients/{usrId}")
    public Result<Void> deleteClient(@PathVariable String usrId) {
        clientFeign.deleteClient(usrId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /*新增客户信息*/
    @PostMapping("/clients")
    public Result<Client> createClient(@Valid @RequestBody Client client) {
        Client savedClient = clientFeign.createClient(client).getData();
        return Result.build(savedClient, ResultCodeEnum.SUCCESS);
    }

    /*根据用户账户查询密码*/
    @GetMapping("/clients/usrAccount/{usrAccount}")
    public Result<String> getClientPassword(@PathVariable String usrAccount) {
        String password = clientFeign.getClientPassword(usrAccount).getData();
        return Result.build(password, ResultCodeEnum.SUCCESS);
    }













    /**
     * 添加客户经理
     * @param advisor 客户经理对象
     * @return 添加后的客户经理对象
     */
    @PostMapping
    public Result<Advisor> addAdvisor(@RequestBody Advisor advisor) {
        Advisor advisor1 = advisorService.addAdvisor(advisor);

        return Result.build(advisor1,ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取所有客户经理
     * @return 客户经理列表
     */
    @GetMapping
    public Result<List<Advisor>> getAllAdvisors() {
        List<Advisor> allAdvisors = advisorService.getAllAdvisors();

        return Result.build(allAdvisors,ResultCodeEnum.SUCCESS);

//                advisorService.getAllAdvisors();
    }



    /**
     * 根据姓名模糊查询客户经理列表
     * @param name 姓名
     * @return 客户经理列表
     */
    @GetMapping("/name/{name}")
    public Result<List<Advisor>> findByNameContaining(@PathVariable String name) {
        List<Advisor> byNameContaining = advisorService.findByNameContaining(name);

        return Result.build(byNameContaining,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据证件号码查询客户经理
     * @param idNumber 证件号码
     * @return 客户经理对象
     */
    @GetMapping("/idNumber/{idNumber}")
    public Result<Advisor> findByIdNumber(@PathVariable String idNumber) {
        Advisor byIdNumber = advisorService.findByIdNumber(idNumber);
        return Result.build(byIdNumber,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/{account}")
    public Result<Map<String,String>>  AccountFindPassword(@PathVariable String account){

        // 调用服务层的登录方法
        String encryptedPassword = advisorService.login(account);

        if (encryptedPassword == null){
            return Result.build(null, ResultCodeEnum.ACCOUNT_NOTFOUND);
        }
        return Result.build(encryptedPassword,ResultCodeEnum.SUCCESS);

    }

//    @DeleteMapping("/delete/{usrId}")
//    public Result delete(@PathVariable String usrId ){
//
//        return client.deleteClient(usrId);
//    }
    @DeleteMapping("/delete/{usrId}")
    public Result delete2(@PathVariable Integer usrId ){

        return product.delete(usrId);
    }
}