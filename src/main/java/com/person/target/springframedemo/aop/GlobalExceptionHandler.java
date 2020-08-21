package com.person.target.springframedemo.aop;

import com.alibaba.fastjson.JSONObject;
import com.person.target.springframedemo.domain.ResultVO;
import com.person.target.springframedemo.domain.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Serein
 * @description
 * @date 2020/7/14
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<List<FieldError>> handleHttpRequestArgumentsValidException(MethodArgumentNotValidException e) {
        log.error("http request arguments valid error, request data:{}", JSONObject.toJSON(e.getParameter()));
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        Integer code = ResultEnum.ARGS_ERROR.getCode();
        String msg = ResultEnum.ARGS_ERROR.getMsg();
        return new ResultVO<>(code, msg, errorList);
    }

    @ExceptionHandler(BizException.class)
    public ResultVO<Void> handleBizException(BizException e) {
        log.error("business exception :", e);
        Integer code = e.getCode();
        String msg = e.getMsg();
        return new ResultVO<>(code, msg);
    }

}
