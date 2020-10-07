package com.huaxing.framework.datasource.datasource;

import com.huaxing.framework.core.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Zion
 * @Description 雪花ID生成
 **/
@Slf4j
@Component
public class IdGenerateSnowflake {
    /**
     * 范围 0-31
     * 可以理解成 机房中 具体的机器
     */
    private long workerId;
    /**
     * 范围 0-31
     * 数据中心   可以理解成 机房
     */
    private long datacenterId = 1L;

    /**
     * 雪花算法生成器
     */
    private static SnowFlake snowflake;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            Long ipLong = Long.parseLong(hostAddress.replaceAll("\\.", ""));
            workerId = ipLong.hashCode() % 32;
            snowflake = new SnowFlake(workerId,datacenterId);
        } catch (Exception e) {
            log.error("init snow flake fail ",e);
        }
    }

    /**
     * 获取雪花自增ID
     * @return
     */
    public static String genSnowFlakeId(){
        return  String.valueOf(snowflake.nextId());
    }
}
