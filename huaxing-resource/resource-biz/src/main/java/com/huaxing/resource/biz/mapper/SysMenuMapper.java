package com.huaxing.resource.biz.mapper;

import com.huaxing.resource.biz.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单
 *
 * @date 2021-04-19 16:47:10
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    List<String> findPermissionByRoleId(@Param("roleIds") List<Long> roleIds);
}
