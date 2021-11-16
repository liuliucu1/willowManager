package com.example.test.config;

import com.alibaba.fastjson.annotation.JSONField;

public class Result {
    //成功1，失败0，返回原因
    @JSONField(ordinal = 1)
    private int code = 0;

    @JSONField(ordinal = 2)
    private Object data;

    public Result() {
    }

    public Result(int code, Object data) {
        this.code = code;

        this.data = data;
    }

    public static Result getResult(int code, Object data) {
        return new Result(code, data);
    }


    public static Result success( Object data) {
        return new Result(1, data);
    }



    public static Result fail( Object data) {
        return new Result(0, data);
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Info{" +
                "code=" + code +
                ", data=" + data +"/"+
                '}';
    }
}
