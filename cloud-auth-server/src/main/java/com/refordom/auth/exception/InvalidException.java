package com.refordom.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.refordom.common.security.exception.BaseOAuth2Exception;
import com.refordom.common.security.exception.OAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 认证信息无效
 *
 * @author 王晟权
 * @date 2019/9/8
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class InvalidException extends BaseOAuth2Exception {

    public InvalidException(String msg, Throwable t) {
        super(msg);
    }

    public InvalidException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

}
