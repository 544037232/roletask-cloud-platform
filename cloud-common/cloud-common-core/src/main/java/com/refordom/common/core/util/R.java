package com.refordom.common.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 接口返回对象
 *
 * @author 王晟权
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 如果出现异常，可以在此属性中给出具体的错误详情
     */
    private String error;

    /**
     * 返回的数据
     */
    private T data;

    public static <T> R<T> ok() {
        return restResult(null, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> R<T> ok(T data, String message) {
        return restResult(data, HttpStatus.OK.value(), StringUtils.isEmpty(message) ? HttpStatus.OK.name() : message, null);
    }

    public static <T> R<T> failed(int statusCode) {
        HttpStatus httpStatus = HttpStatus.resolve(statusCode);
        return restResult(null, statusCode, null == httpStatus ? null : httpStatus.getReasonPhrase(), null);
    }

    public static <T> R<T> failed(int statusCode, String message) {
        HttpStatus httpStatus = HttpStatus.resolve(statusCode);
        return restResult(null, statusCode, message, null == httpStatus ? null : httpStatus.getReasonPhrase());
    }

    public static <T> R<T> failed(T data, int statusCode) {
        HttpStatus httpStatus = HttpStatus.resolve(statusCode);
        return restResult(data, statusCode, null, null == httpStatus ? null : httpStatus.getReasonPhrase());
    }

    public static <T> R<T> failed(T data, int statusCode, String message) {
        HttpStatus httpStatus = HttpStatus.resolve(statusCode);
        return restResult(data, statusCode, message, null == httpStatus ? null : httpStatus.getReasonPhrase());
    }

    public static <T> R<T> failed(int statusCode, String message, String error) {
        return restResult(null, statusCode, message, error);
    }

    public static <T> R<T> failed(T data, int statusCode, String message, String error) {
        return restResult(data, statusCode, message, error);
    }

    private static <T> R<T> restResult(T data, int code, String msg, String error) {
        R<T> apiResult = new R<>();
        apiResult.setStatus(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        if (HttpStatus.OK.value() != code) {
            if (StringUtils.isEmpty(error)) {
                apiResult.setError("unknown error");
            } else {
                apiResult.setError(error);
            }
        }
        return apiResult;
    }

    @Override
    public String toString() {
        return "R{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
