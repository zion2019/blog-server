package com.huaxing.site.mesosphere.feign.system.user;

import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Zion
 * @Description 用户client
 **/
@Component
@RequestMapping("user")
@FeignClient(value = "system-server", contextId = "sys-user-feign")
public interface SysUserFeign {

    @GetMapping("/getUserByAccount")
    BaseResponseVo<SysUserVo> getUserByAccount(@RequestParam("userAccount") String userAccount);

    @GetMapping("/getUserRoleIdsByUserId")
    BaseResponseVo<List<Long>> getUserRoleIdsByUserId(@RequestParam("userId") String userId);
}

