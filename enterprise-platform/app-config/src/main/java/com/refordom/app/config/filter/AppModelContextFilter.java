package com.refordom.app.config.filter;

import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.core.AppContextHolder;
import com.refordom.app.model.AppModel;
import com.refordom.app.model.entity.AppAccount;
import com.refordom.app.model.enums.AppEnum;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:47
 */
public class AppModelContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String appId = request.getParameter(AppConstant.PARAM_APP_ID);

        AppEnum appType = AppEnum.valueOf(request.getParameter(AppConstant.PARAM_APP_TYPE).toUpperCase());

        // 获取模型，并设置到上下文
        AppModel appModel = new AppAccount();

        AppContextHolder.getContext().setAppModel(appModel);

        chain.doFilter(request,response);
    }
}
