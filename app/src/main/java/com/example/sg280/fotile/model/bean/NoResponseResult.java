package com.example.sg280.fotile.model.bean;

/**
 * Created by sg280 on 2016-07-26.
 */
public class NoResponseResult {
    private  String ErrorCode;// null,				错误编码
    private String ErrorMessage;// null,			错误信息
    private String Success;// "1"

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

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    @Override
    public String toString() {
        return "NoResponseResult{" +
                "ErrorCode='" + ErrorCode + '\'' +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                ", Success='" + Success + '\'' +
                '}';
    }
}
