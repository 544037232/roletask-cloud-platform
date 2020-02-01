package com.refordom.app.service.filter;

import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.filter.AbstractFilter;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.model.entity.AppDistro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 9:52
 */
public class AppDistroFilter extends AbstractFilter {

    private final AppManager appManager;

    public AppDistroFilter(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {
        String appId = request.getParameter(ParamConstant.PARAM_APP_ID);

        AppDistro appDistro = appManager.getAppDistroManagerService().getDistroApp(appId);

        if (appDistro == null) {
            throw new AppContextException("应用没有发布");
        }

        AppContextHolder.getContext().setProcessObj(AppDistro.class, appDistro);
    }
}
