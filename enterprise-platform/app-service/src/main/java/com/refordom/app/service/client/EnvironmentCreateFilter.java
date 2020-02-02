package com.refordom.app.service.client;

import cn.hutool.core.util.IdUtil;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:53
 */
public class EnvironmentCreateFilter extends AbstractFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

        EnvironmentParam environmentParam = (EnvironmentParam) ActionContextHolder.getContext().getParamBean();

        AppEnvironment appEnvironment = new AppEnvironment();
        appEnvironment.setClientName(environmentParam.getClientName());
        appEnvironment.setClientSecret(IdUtil.simpleUUID());
        appEnvironment.setDomain(environmentParam.getDomain());
        appEnvironment.setClientId(IdUtil.objectId());

        ActionContextHolder.getContext().addResult(appEnvironment);
    }

}
