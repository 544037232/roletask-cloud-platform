package com.refordom.auth.validate;

import com.refordom.auth.properties.ValidateCodeProperties;
import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.exception.ValidateCodeException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>验证码过滤器，可配置开启图形验证码，可对某个URL请求进行验证码校验配置</p>
 * @author : 王晟权
 * @date : 2019/6/11 23:10
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Resource
    private AuthenticationFailureHandler securityAuthenticationFailHandler;

    @Resource
    private ValidateCodeProperties codeProperties;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        // 开启图形验证码时才校验图形验证码
        if (codeProperties.getImage().isEnable()) {
            urlMap.put(SecurityConstants.DEFAULT_AUTH_LOGIN_FORM_ACCESS_TOKEN, ValidateCodeType.IMAGE);
            urlMap.put(SecurityConstants.SPRING_SECURITY_OAUTH_TOKEN, ValidateCodeType.IMAGE);
            addUrlToMap(codeProperties.getImage().getUrl(), ValidateCodeType.IMAGE);
        }

        urlMap.put(SecurityConstants.DEFAULT_AUTH_LOGIN_MOBILE_ACCESS_TOKEN, ValidateCodeType.SMS);
        addUrlToMap(codeProperties.getSms().getUrl(), ValidateCodeType.SMS);
    }

    private void addUrlToMap(String urlString, ValidateCodeType type) {
        if (!StringUtils.isEmpty(urlString)) {
            String[] urls = StringUtils.split(urlString, ",");

            if (null != urls) {
                for (String url : urls) {
                    urlMap.put(url, type);
                }
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if (type != null) {
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
            } catch (ValidateCodeException exception) {
                securityAuthenticationFailHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, exception);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request 请求
     * @return validateCodeType
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!"get".equalsIgnoreCase(request.getMethod())) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
