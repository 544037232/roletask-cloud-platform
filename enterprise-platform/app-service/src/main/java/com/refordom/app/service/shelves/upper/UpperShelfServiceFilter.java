package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.filter.AbstractFilter;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.model.entity.AppDistro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pricess.wang
 * @date 2020/1/2 16:00
 */
public class UpperShelfServiceFilter extends AbstractFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {
        AppDistro appDistro = (AppDistro) AppContextHolder.getContext().getProcessObj(AppDistro.class);

        if (appDistro.getShelves()) {
            throw new AppContextException("应用已经上架");
        }

        AppContextHolder.getContext().addResult(appDistro);
    }
}
