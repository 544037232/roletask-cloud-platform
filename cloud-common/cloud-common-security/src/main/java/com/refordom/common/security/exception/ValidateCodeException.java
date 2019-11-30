package com.refordom.common.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 验证码异常
 *
 * @author : 王晟权
 * @date : 2019/6/12 10:42
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, value = HttpStatus.UNAUTHORIZED)
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
