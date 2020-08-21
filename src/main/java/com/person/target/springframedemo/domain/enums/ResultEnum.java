package com.person.target.springframedemo.domain.enums;

public enum ResultEnum {

    SUCCESS(0, "success"),
    FAILED(500, "fail"),
    ARGS_ERROR(400, "parameter error"),

    ;

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
