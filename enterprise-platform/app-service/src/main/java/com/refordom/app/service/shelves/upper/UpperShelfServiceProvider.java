package com.refordom.app.service.shelves.upper;

import com.pricess.omc.ResultToken;
import com.pricess.omc.api.ServiceProvider;
import com.pricess.omc.context.ActionContextHolder;
import com.pricess.omc.exception.AppContextException;
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
