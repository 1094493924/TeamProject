package com.kqtlt.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Boolean success;
    private Integer code;
    private String msg;
    private Integer count;
    private Map<String,Object> data=new HashMap<>();


    private R(){
    }


    public static R ok(){
        R r=new R();
        r.setSuccess(ReturnCodeEnum.SUCCESS.getSuccess());
        r.setCode(ReturnCodeEnum.SUCCESS.getCode());
        r.setMsg(ReturnCodeEnum.SUCCESS.getMessage());
        r.setCount(ReturnCodeEnum.SUCCESS.getCount());
        return r;
    }

    public static R error(){
        R r=new R();
        r.setSuccess(ReturnCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ReturnCodeEnum.UNKNOWN_REASON.getCode());
        r.setMsg(ReturnCodeEnum.UNKNOWN_REASON.getMessage());
        r.setCount(ReturnCodeEnum.UNKNOWN_REASON.getCount());
        return r;
    }

    public static R setResult(ReturnCodeEnum resultCodeEnum){
        R r = new R();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMessage());
        r.setCount(resultCodeEnum.getCount());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMsg(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R count(Integer count){
        this.setCount(count);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public Map<String,Object> toMap(){
        return this.data;
    }
}
