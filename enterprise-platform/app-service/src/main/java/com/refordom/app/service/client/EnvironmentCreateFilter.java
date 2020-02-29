package com.refordom.app.service.client;

import cn.hutool.core.util.IdUtil;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;
import com.refordom.common.action.builder.exception.AppContextException;
import com.refordom.common.action.builder.lock.ActionDistributedLockHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:53
 */
public class EnvironmentCreateFilter extends AbstractFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

        EnvironmentParamAdapter environmentParam = (EnvironmentParamAdapter) ActionContextHolder.getContext().getParamAdapter();

        if (!ActionDistributedLockHolder.lock(environmentParam.getClientName())) {
            throw new AppContextException("服务器繁忙，请重试");
        }

        AppEnvironment appEnvironment = new AppEnvironment();
        appEnvironment.setClientName(environmentParam.getClientName());
        appEnvironment.setClientSecret(IdUtil.simpleUUID());
        appEnvironment.setDomain(environmentParam.getDomain());
        appEnvironment.setClientId(IdUtil.objectId());

        ActionContextHolder.getContext().addResult(appEnvironment);
    }

}
