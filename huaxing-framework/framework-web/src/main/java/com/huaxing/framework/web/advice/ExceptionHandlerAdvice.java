package com.huaxing.framework.web.advice;

import com.google.common.collect.Lists;
import com.huaxing.framework.core.exception.HbException;
import com.huaxing.framework.core.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 捕获业务异常
 */
@Order(-1)
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HbException.class})
    public ResponseResult handleBadRequest(HttpServletResponse rps,
                                           HttpServletRequest req, HbException e) {
        log.error("BaseException功能出错", e);
        return new ResponseResult(e);
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult handleGlobalException(Exception e) {
        log.error("接口异常信息: ", e.getMessage(), e);
        return ResponseResult.failed(e.getMessage());
    }


    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult doMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> msgList = Lists.newArrayList();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            msgList.add(objectError.getDefaultMessage());
        }
        return ResponseResult.failed(msgList.toString());

    }

}
