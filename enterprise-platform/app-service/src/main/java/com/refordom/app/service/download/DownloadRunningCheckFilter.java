package com.refordom.app.service.download;

import com.refordom.app.config.filter.AbstractFilter;
import com.refordom.app.config.manager.AppManager;

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

    }

}
