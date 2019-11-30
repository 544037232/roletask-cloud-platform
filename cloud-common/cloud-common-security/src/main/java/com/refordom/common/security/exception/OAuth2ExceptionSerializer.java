package com.refordom.common.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.refordom.common.core.util.R;

import java.io.IOException;

/**
 * <p>oauth2异常格式化</p>
 *
 * @author pricess.wang
 * @date 2019/9/21 16:59
 */
public class OAuth2ExceptionSerializer extends StdSerializer<BaseOAuth2Exception> {

    protected OAuth2ExceptionSerializer() {
        super(BaseOAuth2Exception.class);
    }

    @Override
    public void serialize(BaseOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(R.failed(e.getHttpErrorCode(), e.getMessage(), e.getOAuth2ErrorCode()));
    }
}
