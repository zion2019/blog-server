package com.huaxing.framework.cache.config;

import com.huaxing.framework.core.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class MyStringSerializer implements RedisSerializer<String> {

    private final Logger logger = LoggerFactory.getLogger ( this.getClass () );

    private final Charset charset;
    
    @Autowired
    Environment environment;

    public MyStringSerializer() {
        this ( Charset.forName ( "UTF8" ) );
    }

    public MyStringSerializer(Charset charset) {
        Assert.isNull ( charset, "Charset must not be null!" );
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        String keyPrefix = environment.getProperty("spring.application.name");
        if(StringUtils.isBlank(keyPrefix)) {
        	keyPrefix = "huaxing-common";
        }
        String saveKey = new String ( bytes, charset );
        int indexOf = saveKey.indexOf ( keyPrefix +":" );
        if (indexOf > 0) {
            logger.info ( "key缺少前缀" );
        } else {
            saveKey = saveKey.substring ( indexOf );
        }
        logger.info ( "saveKey:{}",saveKey);
        return (saveKey.getBytes () == null ? null : saveKey);
    }

    @Override
    public byte[] serialize(String string) {
    	String keyPrefix = environment.getProperty("spring.application.name");
    	if(StringUtils.isBlank(keyPrefix)) {
        	keyPrefix = "huaxing-common";
        }
        String key = keyPrefix +":"+ string;
        logger.info ( "key:{},getBytes:{}",key, key.getBytes ( charset ));
        return (key == null ? null : key.getBytes ( charset ));
    }
}