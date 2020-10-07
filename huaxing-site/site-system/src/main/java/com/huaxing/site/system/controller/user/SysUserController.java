package com.huaxing.site.system.controller.user;

import com.huaxing.site.mesosphere.feign.system.user.SysUserFeign;
import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import com.huaxing.site.system.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zion
 * @Description 用户控制器
 **/
@RestController
@RequestMapping("user")
public class SysUserController implements SysUserFeign {

    @Autowired
    private SysUserService userService;

    @Override
    @ResponseBody
    @GetMapping("/getUserByAccount")
    public BaseResponseVo<SysUserVo> getUserByAccount(@RequestParam("userAccount") String userAccount) {
        return BaseResponseVo.success().setData(userService.getUserByAccount(userAccount));
    }

    @Override
    @ResponseBody
    @GetMapping("/getUserRoleIdsByUserId")
    public BaseResponseVo<List<Long>> getUserRoleIdsByUserId(@RequestParam("userId") String userId) {
        return null;
    }

    @ResponseBody
    @PostMapping("/login")
    public BaseResponseVo login(@RequestBody SysUserVo request){
        return BaseResponseVo.success().setData(userService.login(request));
    }

    @ResponseBody
    @PostMapping("/register")
    public BaseResponseVo register(@RequestBody SysUserVo request){
        userService.register(request);
        return BaseResponseVo.success();
    }
}
