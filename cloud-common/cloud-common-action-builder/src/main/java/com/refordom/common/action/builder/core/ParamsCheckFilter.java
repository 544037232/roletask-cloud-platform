package com.refordom.common.action.builder.core;

import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.validator.*;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2019/12/13 19:07
 */
public class ParamsCheckFilter implements Filter {

    private ActionValidator actionValidator = new DefaultActionValidator();

    private ActionParamParser actionParamParser;

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        if (actionParamParser == null){
            chain.doFilter(req, rep);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) req;

        ParamAdapter paramAdapter = actionParamParser.build(request);

        ValidatorResult validatorResult = actionValidator.validate(paramAdapter);

        if (validatorResult != null) {
            HttpServletResponse response = (HttpServletResponse) rep;

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(validatorResult.getErrorMsg());
            return;
        }

        ActionContextHolder.getContext().setParamAdapter(paramAdapter);

        chain.doFilter(req, rep);
    }

    public void setActionParamParser(ActionParamParser actionParamParser) {
        this.actionParamParser = actionParamParser;
    }
}
