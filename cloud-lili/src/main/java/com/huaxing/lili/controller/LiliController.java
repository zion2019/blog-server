package com.huaxing.lili.controller;


import com.huaxing.lili.remote.UserRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lili")
public class LiliController {

    @Autowired
    private UserRemote userRemote;

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public String getUserInfo(){
        return userRemote.hello(1L);
    }
}
