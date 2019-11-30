package com.refordom.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * <p>基础的认证异常</p>
 *
 * @author pricess.wang
 * @date 2019/9/21 17:19
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class BaseOAuth2Exception extends OAuth2Exception {

    public BaseOAuth2Exception(String msg) {
        super(msg);
    }

    public BaseOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }
}
