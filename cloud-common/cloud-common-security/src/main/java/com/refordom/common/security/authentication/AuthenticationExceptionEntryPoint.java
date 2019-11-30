package com.refordom.common.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refordom.common.core.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * <p>入口点认证自定义异常</p>
 *
 * @author pricess.wang
 * @date 2019/10/10 18:53
 */
@Component
public class AuthenticationExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        R<String> result = new R<>();
        if (authException != null) {
            result.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage(authException.getMessage());
            result.setError(HttpStatus.UNAUTHORIZED.name());
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
