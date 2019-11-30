package com.refordom.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.refordom.common.security.exception.BaseOAuth2Exception;
import com.refordom.common.security.exception.OAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 没有权限
 *
 * @author 王晟权
 * @date 2019/9/8
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class ForbiddenException extends BaseOAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpStatus.FORBIDDEN.getReasonPhrase();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }

}

