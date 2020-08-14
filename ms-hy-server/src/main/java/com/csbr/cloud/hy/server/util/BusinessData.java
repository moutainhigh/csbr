package com.csbr.cloud.hy.server.util;

import java.io.Serializable;

/**
 * @author zhangheng
 * @date 2020/4/27 16:49
 */
public class BusinessData<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Boolean isSuccess;
    private String code;
    private String errorMessage;
    private String at;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}