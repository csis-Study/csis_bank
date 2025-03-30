package com.csis.usrservice.FeignClient;


import com.csis.usrservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="client-service")
public interface clientClient {

    @GetMapping("/clients/{usrId}")
    Result<Void> deleteClient(@PathVariable String usrId);


}
