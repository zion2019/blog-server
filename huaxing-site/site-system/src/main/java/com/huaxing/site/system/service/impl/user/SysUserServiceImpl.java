package com.huaxing.site.system.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.framework.core.exception.HbException;
import com.huaxing.framework.core.utils.BeanUtils;
import com.huaxing.framework.datasource.datasource.IdGenerateSnowflake;
import com.huaxing.site.mesosphere.constant.BaseConstant;
import com.huaxing.site.mesosphere.vo.system.AuthTokenResVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import com.huaxing.site.system.entity.SysUserEntity;
import com.huaxing.site.system.mapper.SysUserMapper;
import com.huaxing.site.system.service.impl.auth.AuthService;
import com.huaxing.site.system.service.user.SysUserService;
import com.huaxing.site.system.utils.ASEUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huaxing.framework.core.utils.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author Zion
 * @Description 用户基础服务实现
 **/
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService  {

    @Autowired
    private AuthService authService;

    @Override
    public AuthTokenResVo login(SysUserVo request) {
        Assert.isTrue(request == null , "common_error_0001");
        Assert.isTrue(StringUtils.isBlank(request.getUserAccount()),"common_error_0002",new Object[]{"userAccount"});
        Assert.isTrue(StringUtils.isBlank(request.getUserPassword()),"common_error_0002",new Object[]{"passWord"});

        // 查询用户
        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserEntity::getUserAccount,request.getUserAccount());
        wrapper.eq(SysUserEntity::getDeleted, BaseConstant.YesorNo.NO.getCode());
        List<SysUserEntity> dbUserList = this.list(wrapper);
        Assert.isTrue(CollectionUtils.isEmpty(dbUserList) , "login_error_0003");
        SysUserEntity dbUser = dbUserList.get(0);

        // 匹配密码
        String salt = dbUser.getUserSalt();
        String password = ASEUtil.encrypt(salt, request.getUserPassword());
        Assert.isTrue(StringUtils.isBlank(password) || !password.equals(dbUser.getUserPassword()) , "login_error_0004");

        return authService.buildJwtToken(request);
    }


    @Override
    public void register(SysUserVo request) {
        SysUserEntity user = new SysUserEntity();

        Assert.isTrue(StringUtils.isBlank(request.getUserAccount()),"common_error_0002",new Object[]{"userAccount"});
        Assert.isTrue(StringUtils.isBlank(request.getUserPassword()),"common_error_0002",new Object[]{"passWord"});
        Assert.isTrue(StringUtils.isBlank(request.getUserName()),"common_error_0002",new Object[]{"userName"});

        //1.校验账号是否已注册
        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserEntity::getUserAccount,request.getUserAccount());
        wrapper.eq(SysUserEntity::getDeleted, BaseConstant.YesorNo.NO.getCode());
        int count = this.count(wrapper);
        Assert.isTrue(count > 0 , "register_error_0001");

        try {

            user.setUserName(request.getUserName());
            user.setUserAccount(request.getUserAccount());

            //密码加盐
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            String password = ASEUtil.encrypt(salt, request.getUserPassword());
            user.setUserAccount(request.getUserAccount());
            user.setUserPassword(password);
            user.setUserId(IdGenerateSnowflake.genSnowFlakeId());
            user.setUserSalt(salt);
            this.save(user);

        }catch(Exception e) {
            log.error("user register error:{}",e.getMessage());
            throw new HbException(BaseConstant.INNER_ERRO,e.getMessage());
        }
    }

    @Override
    public SysUserVo getUserByAccount(String userAccount) {
        Assert.isTrue(StringUtils.isBlank(userAccount),"common_error_0002",new Object[]{"userAccount"});
        SysUserVo sysUserVo = new SysUserVo();

        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserEntity::getUserAccount,userAccount);
        wrapper.eq(SysUserEntity::getDeleted, BaseConstant.YesorNo.NO.getCode());
        SysUserEntity sysUserEntity = this.getOne(wrapper);
        Assert.isTrue(sysUserEntity == null,"common_error_0002",new Object[]{"userAccount"});

        BeanUtils.copyProperties(sysUserEntity,sysUserVo);

        return sysUserVo;
    }
}
