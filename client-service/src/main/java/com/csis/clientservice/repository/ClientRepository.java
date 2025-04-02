package com.csis.clientservice.repository;

import com.csis.clientservice.pojo.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ClientRepository")
public interface ClientRepository extends JpaRepository<Client, String> {

    // 根据用户账号查询客户端
    Optional<Client> findByUsrAccount(String usrAccount);

    // 根据用户ID查询客户端
    Client findByUsrId(String usrId);

    // 自定义查询方法
    boolean existsByUsrAccount(String usrAccount);

    //根据usrId删除数据
/*    Boolean deleteByUsrId(String usrId);*/

   /* //根据用户账号查询密码
    @Query("SELECT c.usrPasswd FROM Client c WHERE c.usrAccount = :usrAccount")
    String getClientPassword(String usrAccount);*/

    List<Client> findByRelationshipManagerId(String relationshipManagerId);

    /*根据客户账户删除客户*/
    @Modifying
    @Query("DELETE FROM Client c WHERE c.usrAccount = :usrAccount")
    void deleteByUsrAccount(String usrAccount);

    /**
     * 根据客户经理ID分页查询客户
     * @param managerId 客户经理ID（需符合U+11位数字格式）
     * @param pageable 分页参数对象
     * @return 分页结果
     */
    Page<Client> findByRelationshipManagerId(String managerId, Pageable pageable);

}