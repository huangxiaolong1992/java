package com.example.myblog.common;

import lombok.Data;

@Data
public class R<T> {

    private Integer code; //编码：200成功，500和其它数字为失败

    private String msg; //错误信息

    private T data; //数据


    public static <T> R<T> success(String msg, T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        r.msg = msg;

        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 500;
        return r;
    }

}