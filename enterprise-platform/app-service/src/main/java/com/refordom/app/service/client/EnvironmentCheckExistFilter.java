package com.refordom.app.service.client;

import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.core.AbstractFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:53
 */
public class EnvironmentCheckExistFilter extends AbstractFilter {

    private final AppEnvironmentService appEnvironmentService;

    public EnvironmentCheckExistFilter(AppEnvironmentService appEnvironmentService) {
        this.appEnvironmentService = appEnvironmentService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

    }
}
