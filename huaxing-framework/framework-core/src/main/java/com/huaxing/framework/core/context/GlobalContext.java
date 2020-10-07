package com.huaxing.framework.core.context;

import com.alibaba.fastjson.JSONObject;
import com.huaxing.framework.subject.BaseSubject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * @author Zion
 * @Description 全局上下文对象
 **/
@Slf4j
@Component
public class GlobalContext {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }



    public static final String REQUEST_HEAD_AUTH_KEY = "AUTHORIZATION";


    /**
     * 获取用户subject
     * @return
     * @see GlobalContext#getCurrentUserSubject()
     */
    public String getCurrentUserSubject() {
        return this.getRequest().getHeader(BaseSubject.HEARD_SUBJECT_KEY);
    }


    /**
     * 获取用户token
     * @return
     */
    public String getCurrentUserToken() {
        return this.getRequest().getHeader(REQUEST_HEAD_AUTH_KEY);
    }

    /**
     * 获取subject
     * @return
     */
    public BaseSubject getGlobalSubject() {
        BaseSubject baseSubject = null;
        try {
            String subject = this.getCurrentUserSubject();
            if (StringUtils.isNotEmpty(subject)) {
                subject = URLDecoder.decode(subject, "UTF-8");

                baseSubject = JSONObject.parseObject(subject, BaseSubject.class);
            }
        } catch (Exception e) {
            // do nothing
//			log.error("get BaseSubject from request header erro:{}", e);
        }
        return baseSubject;
    }

    /**
     * 获取用户Id
     * @return
     */
    public String getCurrentUserId() {
        BaseSubject sub = this.getGlobalSubject();
        return sub!=null?sub.getUserId():null;
    }
}
