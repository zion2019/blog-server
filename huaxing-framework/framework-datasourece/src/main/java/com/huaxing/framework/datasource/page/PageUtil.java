package com.huaxing.framework.datasource.page;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaxing.framework.core.exception.HbException;
import com.huaxing.framework.core.page.PageDto;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Zion
 * @Description 转换数据库分页为数据对象分页
 **/
public class PageUtil {

    public static <T> PageDto transferPageDto(Page pages, Class<T> clazz){

        if(pages == null || CollectionUtils.isEmpty(pages.getRecords())){
            return new PageDto();
        }

        PageDto<T> result = new PageDto();
        for(Object o :pages.getRecords()){
            try {
                T t = clazz.newInstance();
                BeanUtil.copyProperties(o,t);
                result.getRecords().add(t);
            }catch (Exception e){
                e.printStackTrace();
                throw new HbException("转换page对象失败",e.getMessage());
            }
        }
        result.setCurrent(pages.getCurrent());
        result.setSize(pages.getSize());
        result.setTotal(pages.getTotal());
        return result;
    }
}
