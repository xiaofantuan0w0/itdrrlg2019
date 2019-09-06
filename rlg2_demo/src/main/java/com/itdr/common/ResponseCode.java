package com.itdr.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseCode<T> {
    private Integer status;
    private  T data;
    private String mag;

    public ResponseCode(){}
    public ResponseCode(Integer status){
        this.status = status;
    }
    public ResponseCode(Integer status, String mag){
        this.status = status;
        this.mag = mag;
    }
    public ResponseCode(Integer status, T data){
        this.status = status;
        this.data = data;
    }
    public ResponseCode(Integer status, T data, String mag){
        this.status = status;
        this.data = data;
        this.mag = mag;
    }


    //是否成功，成功返回状态码和成功获取的数据，
    public static <T> ResponseCode success (){
        return new ResponseCode(0);
    }
    public static <T> ResponseCode success (T data){
        return new ResponseCode(0,data);
    }
    public static <T> ResponseCode success (String mag, T data){
        return new ResponseCode(0,data,mag);
    }

    // 失败返回状态码和失败的信息
    public static <T> ResponseCode error (Integer status){
        return new ResponseCode(status);
    }
    public static <T> ResponseCode error (Integer status, String mag){
        return new ResponseCode(status,mag);
    }
}
