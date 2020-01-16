package com.refordom.app.service.shelves.lower;

import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.validator.DefaultParamBean;
import com.refordom.app.model.entity.AppDistro;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2020/1/2 16:00
 */
public class LowerShelfServiceFilter extends GenericFilter {

    private final AppManager appManager;

    public LowerShelfServiceFilter(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        DefaultParamBean param = (DefaultParamBean) AppContextHolder.getContext().getParamBean();

        AppDistro appDistro = appManager.getAppDistroManagerService().getDistroApp(param.getAppId());

        if (appDistro == null) {
            throw new AppContextException("应用没有发布");
        }

        if (!appDistro.getShelves()) {
            throw new AppContextException("应用已经下架");
        }

        AppContextHolder.getContext().addResult(appDistro);

        chain.doFilter(request, response);
    }
}
