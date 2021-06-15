package com.person.target.springframedemo.aop;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Serein
 * @description
 * @date 2020/7/14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException{

    private static final long serialVersionUID = 2348925024316467605L;
    private Integer code;
    private String msg;
    public BizException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
