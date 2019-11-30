package com.refordom.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.refordom.common.security.exception.BaseOAuth2Exception;
import com.refordom.common.security.exception.OAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 未授权
 *
 * @author 王晟权
 * @date 2019/9/8
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class UnauthorizedException extends BaseOAuth2Exception {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpStatus.UNAUTHORIZED.getReasonPhrase();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}
