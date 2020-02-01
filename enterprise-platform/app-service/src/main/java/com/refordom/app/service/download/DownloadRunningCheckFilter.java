package com.refordom.app.service.download;

import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.filter.AbstractFilter;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.app.model.entity.AppRunning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pricess.wang
 * @date 2020/1/16 19:44
 */
public class DownloadRunningCheckFilter extends AbstractFilter {

    private final AppManager appManager;

    public DownloadRunningCheckFilter(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {
        AppRunning currentRunning = appManager.getAppRunningManagerService()
                .getRunningApp(request.getParameter(ParamConstant.PARAM_APP_ID), "clientId");

        if (null != currentRunning) {
            AppDistro appDistro = (AppDistro) AppContextHolder.getContext().getProcessObj(AppDistro.class);

            if (!appDistro.getShelves()) {
                throw new AppContextException("应用已经下架");
            }

            if (appDistro.getVersion().equals(currentRunning.getVersion())) {
                throw new AppContextException("您已经安装了此版本的应用");
            }
        }
    }

}
