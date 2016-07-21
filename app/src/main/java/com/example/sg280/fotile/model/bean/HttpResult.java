package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-19.
 */
public class HttpResult<T> {
    private String  Start; //当前页
    private String  Limit; // 没有条数
    private String  ErrorCode; //错误码
    private String  ErrorMessage;//   错误信息
    private int  Success;//   是否成功
   private  T Rows;//data数据

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getLimit() {
        return Limit;
    }

    public void setLimit(String limit) {
        Limit = limit;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public T getRows() {
        return Rows;
    }

    public void setRows(T rows) {
        Rows = rows;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "Start='" + Start + '\'' +
                ", Limit='" + Limit + '\'' +
                ", ErrorCode='" + ErrorCode + '\'' +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                ", Success=" + Success +
                ", Rows=" + Rows +
                '}';
    }
}
