package com.refordom.app.service.filter;

import com.pricess.omc.context.ActionContextHolder;
import com.pricess.omc.exception.AppContextException;
import com.pricess.omc.filter.AbstractFilter;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.model.AppDistroManagerService;
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

    private final AppDistroManagerService distroManagerService;

    public AppDistroFilter(AppDistroManagerService distroManagerService) {
        this.distroManagerService = distroManagerService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {
        String appId = request.getParameter(ParamConstant.PARAM_APP_ID);

        AppDistro appDistro = distroManagerService.getDistroApp(appId);

        if (appDistro == null) {
            throw new AppContextException("应用没有发布");
        }

        ActionContextHolder.getContext().setProcessObj(AppDistro.class, appDistro);
    }
}
