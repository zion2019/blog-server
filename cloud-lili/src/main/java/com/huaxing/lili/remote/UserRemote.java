package com.huaxing.lili.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "HUAXING-PRODUCER-USER")
public interface UserRemote {

    @GetMapping(value = "/api/user/{userId}")
    String hello(@PathVariable(value = "userId") Long userId);
}
