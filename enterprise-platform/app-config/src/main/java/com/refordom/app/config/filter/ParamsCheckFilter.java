package com.refordom.app.config.filter;

import com.refordom.app.core.validator.ActionParamParser;
import com.refordom.app.core.validator.ActionValidator;
import com.refordom.app.core.validator.DefaultActionValidator;
import com.refordom.app.core.validator.ValidatorResult;
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

        ValidatorResult validatorResult = actionValidator.validate(actionParamParser.build(request));

        if (validatorResult != null) {
            HttpServletResponse response = (HttpServletResponse) rep;

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(validatorResult.getErrorMsg());
            return;
        }
        chain.doFilter(req, rep);
    }

    public void setActionParamParser(ActionParamParser actionParamParser) {
        this.actionParamParser = actionParamParser;
    }
}
