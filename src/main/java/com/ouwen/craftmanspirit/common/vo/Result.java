package com.ouwen.craftmanspirit.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(200,"success",null);
    }
    public static <T> Result<T> success(T data){
        return new Result<>(200,"success",data);
    }
    public static <T> Result<T> success(T data,String message){
        return new Result<>(200,message,data);
    }
    public static <T> Result<T> success(String message){
        return new Result<>(200,message,null);
    }

    public static <T> Result<T> fail(){
        return new Result<>(201,"success",null);
    }
    public static <T> Result<T> fail(Integer code){
        return new Result<>(code,"success",null);
    }
    public static <T> Result<T> fail(Integer code,String message){
        return new Result<>(code,message,null);
    }
    public static <T> Result<T> fail(String message){
        return new Result<>(201,message,null);
    }
}
