package com.refordom.app.config.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/13 19:07
 */
public class ParamsEmptyCheckFilter extends GenericFilterBean {

    private final Map<String, String> paramsRule = new HashMap<>();

    private final Map<String, Object> paramInit = new HashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        paramsRule.forEach((param, errorMessage) -> {
            // 初始化参数到上下文
            if (StringUtils.isEmpty(request.getParameter(param))) {
                throw new RuntimeException(errorMessage);
            }
        });

        paramInit.forEach((param, value) -> {
            if (request.getParameter(param) == null) {
                // 设定默认值到上下文
            }
        });
        chain.doFilter(req, rep);
    }

    public void defaultValue(String paramName, Object value) {
        paramInit.put(paramName, value);
    }

    public void checkRule(String paramName, String errorMessage) {
        paramsRule.put(paramName, errorMessage);
    }

}
