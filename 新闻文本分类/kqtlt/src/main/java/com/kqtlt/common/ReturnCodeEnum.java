package com.kqtlt.common;

import lombok.Getter;

@Getter
public enum ReturnCodeEnum {
    SUCCESS(true,0,"成功",0),
    UNKNOWN_REASON(false,1,"未知错误",0),
    BAO_SQL_GRAMMAR(false,2,"sql语法错误",0),
    JSON_PARSE_ERROR(false,3,"json解析异常",0),
    PARAM_ERROR(false,4,"参数不正确",0);

    private Boolean success;
    private Integer code;
    private String message;
    private Integer count;

    ReturnCodeEnum(Boolean success, Integer code, String message,Integer count){
        this.success=success;
        this.code=code;
        this.message=message;
        this.count=count;
    }
}
