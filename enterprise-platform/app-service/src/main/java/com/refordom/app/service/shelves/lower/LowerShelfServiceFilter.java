package com.refordom.app.service.shelves.lower;

import com.refordom.app.model.entity.AppDistro;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;
import com.refordom.common.action.builder.exception.AppContextException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pricess.wang
 * @date 2020/1/2 16:00
 */
public class LowerShelfServiceFilter extends AbstractFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

        AppDistro appDistro = (AppDistro) ActionContextHolder.getContext().getProcessObj(AppDistro.class);

        if (!appDistro.getShelves()) {
            throw new AppContextException("应用已经下架");
        }

        ActionContextHolder.getContext().addResult(appDistro);
    }
}
