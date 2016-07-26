package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-25.
 */
public class HttpDataResult<T> {
    private String ErrorCode;// 错误码,
    private String ErrorMessage;// 错误信息,
    private int Success; //"1"是否成功
    private T Result;

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

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}
