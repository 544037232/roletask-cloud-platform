package com.refordom.app.service.download;

import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.model.AppRunningManagerService;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.app.model.entity.AppRunning;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;
import com.refordom.common.action.builder.exception.AppContextException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pricess.wang
 * @date 2020/1/16 19:44
 */
public class DownloadRunningCheckFilter extends AbstractFilter {

    private final AppRunningManagerService runningManagerService;

    public DownloadRunningCheckFilter(AppRunningManagerService runningManagerService) {
        this.runningManagerService = runningManagerService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {
        AppRunning currentRunning = runningManagerService.getRunningApp(request.getParameter(ParamConstant.PARAM_APP_ID), "clientId");

        if (null != currentRunning) {
            AppDistro appDistro = (AppDistro) ActionContextHolder.getContext().getProcessObj(AppDistro.class);

            if (!appDistro.getShelves()) {
                throw new AppContextException("应用已经下架");
            }

            if (appDistro.getVersion().equals(currentRunning.getVersion())) {
                throw new AppContextException("您已经安装了此版本的应用");
            }
        }
    }

}
