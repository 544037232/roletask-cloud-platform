package com.refordom.app.config.filter;

import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.core.AppContextHolder;
import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.model.AppDetails;
import com.refordom.app.model.AppDetailsManager;
import com.refordom.app.model.enums.AppEnum;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:47
 */
public class AppDetailsFilter implements Filter {

    private AppDetailsManager appModelManager;

    public AppDetailsFilter(AppDetailsManager appModelManager) {
        this.appModelManager = appModelManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String appId = request.getParameter(AppConstant.PARAM_APP_ID);

        AppEnum appType = AppEnum.valuesOf(request.getParameter(AppConstant.PARAM_APP_TYPE).toUpperCase());

        if (appType == null) {
            throw new DeprecatedException("不支持的应用类型");
        }
        // 获取模型，并设置到上下文
        AppDetails appDetails = appModelManager.getApp(appId);

        AppContextHolder.getContext().setAppDetails(appDetails);

        chain.doFilter(request, response);
    }
}
