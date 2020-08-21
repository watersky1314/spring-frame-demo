package com.person.target.springframedemo.domain;

import com.person.target.springframedemo.domain.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Serein
 * @description
 * @date 2020/7/14
 **/
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5233706355080443987L;

    private Integer retcode;
    private String msg;
    private T data;

    public ResultVO(){
        this.retcode = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public ResultVO(T data){
        this.retcode = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultVO(Integer code, String msg){
        this.retcode = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.retcode = code;
        this.msg = msg;
        this.data = data;
    }
}
