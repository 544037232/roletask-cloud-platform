package com.refordom.app.service.shelves.upper;

import com.omc.builder.ResultToken;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.context.ActionContextHolder;
import com.omc.builder.exception.AppContextException;
import com.refordom.app.model.entity.AppDistro;

/**
 * @author pricess.wang
 * @date 2020/1/2 16:00
 */
public class UpperShelfServiceProvider implements ServiceProvider {

    @Override
    public ResultToken provider(ResultToken instance) {
        AppDistro appDistro = (AppDistro) ActionContextHolder.getContext().getProcessObj(AppDistro.class);

        if (appDistro.getShelves()) {
            throw new AppContextException("应用已经上架");
        }

        return new UpperShelfResultToken(appDistro);
    }

    @Override
    public boolean supports(Class<?> context) {
        return true;
    }
}
