package com.refordom.app.service.download;

import com.refordom.app.config.manager.AppManager;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author pricess.wang
 * @date 2020/1/16 19:44
 */
public class DownloadRunningCheckFilter extends GenericFilter {

    private final AppManager appManager;

    public DownloadRunningCheckFilter(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

}
