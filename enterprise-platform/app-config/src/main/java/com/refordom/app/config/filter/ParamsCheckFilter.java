package com.refordom.app.config.filter;

import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.validator.*;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2019/12/13 19:07
 */
public class ParamsCheckFilter extends GenericFilterBean {

    private ActionValidator actionValidator = new DefaultActionValidator();

    private ActionParamParser actionParamParser;

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        ParamBean paramBean = actionParamParser.build(request);

        ValidatorResult validatorResult = actionValidator.validate(paramBean);

        if (validatorResult != null) {
            HttpServletResponse response = (HttpServletResponse) rep;

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(validatorResult.getErrorMsg());
            return;
        }

        AppContextHolder.getContext().setParamBean(paramBean);

        chain.doFilter(req, rep);
    }

    public void setActionParamParser(ActionParamParser actionParamParser) {
        this.actionParamParser = actionParamParser;
    }
}
