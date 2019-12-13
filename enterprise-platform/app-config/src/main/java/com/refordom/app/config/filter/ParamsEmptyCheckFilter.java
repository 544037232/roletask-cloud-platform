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

    private Map<String, String> paramsRule = new HashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        paramsRule.forEach((param, errorMessage) -> {
            if (StringUtils.isEmpty(request.getParameter(param))) {
                throw new RuntimeException(errorMessage);
            }
        });

        chain.doFilter(req, rep);
    }

    public void setParamRule(String paramName, String errorMessage) {
        paramsRule.put(paramName, errorMessage);
    }
}
