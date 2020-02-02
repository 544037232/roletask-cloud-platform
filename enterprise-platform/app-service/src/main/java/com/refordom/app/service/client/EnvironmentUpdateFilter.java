package com.refordom.app.service.client;

import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:53
 */
public class EnvironmentUpdateFilter extends AbstractFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

        EnvironmentParam.EnvironmentUpdateParam environmentParam = (EnvironmentParam.EnvironmentUpdateParam) ActionContextHolder.getContext().getParamBean();

        AppEnvironment appEnvironment = new AppEnvironment();
        appEnvironment.setClientName(environmentParam.getClientName());
        appEnvironment.setDomain(environmentParam.getDomain());
        appEnvironment.setClientId(environmentParam.getClientId());

        ActionContextHolder.getContext().addResult(appEnvironment);
    }

}
