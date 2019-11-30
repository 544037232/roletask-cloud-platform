package com.refordom.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.refordom.common.security.exception.BaseOAuth2Exception;
import com.refordom.common.security.exception.OAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 服务器异常
 *
 * @author 王晟权
 * @date 2019/9/8
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class ServerErrorException extends BaseOAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
