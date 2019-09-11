package com.huaxing.user.controller;

import com.huaxing.user.model.User;
import com.huaxing.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public String getUserInfo(@PathVariable("userId") Long userId) {

        User user = userService.findUserByID(userId);
        return "hello" + user.getUserName();
    }
}
