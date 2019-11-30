package com.refordom.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.refordom.common.security.exception.BaseOAuth2Exception;
import com.refordom.common.security.exception.OAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 405
 *
 * @author 王晟权
 * @date 2019/9/8
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends BaseOAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase();
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
