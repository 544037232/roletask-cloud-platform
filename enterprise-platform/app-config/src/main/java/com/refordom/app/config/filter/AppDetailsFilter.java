package com.refordom.app.config.filter;

import com.pricess.omc.context.ActionContextHolder;
import com.pricess.omc.exception.DeprecatedException;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.core.AppDetails;
import com.refordom.app.core.AppEnum;
import com.refordom.app.model.AppDetailsManagerService;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:47
 */
public class AppDetailsFilter implements Filter {

    private AppDetailsManagerService appDetailsManagerService;

    public AppDetailsFilter(AppDetailsManagerService appDetailsManagerService) {
        this.appDetailsManagerService = appDetailsManagerService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String appId = request.getParameter(ParamConstant.PARAM_APP_ID);

        AppEnum appType = AppEnum.valuesOfBean(request.getParameter(ParamConstant.PARAM_APP_TYPE).toUpperCase());

        if (appType == null) {
            throw new DeprecatedException("不支持的应用类型");
        }
        // 获取模型，并设置到上下文
        AppDetails appDetails = appDetailsManagerService.getApp(appId);

        if (appDetails == null) {
            throw new DeprecatedException("未获取到相关应用");
        }

        if (!appDetails.getAppType().equals(appType)) {
            throw new DeprecatedException("获取到的应用与所需的应用类型不一致");
        }

        ActionContextHolder.getContext().setProcessObj(AppDetails.class, appDetails);

        chain.doFilter(request, response);
    }
}
