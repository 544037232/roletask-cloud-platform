package com.refordom.app.service.client;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.ActionStoreProvider;
import com.refordom.common.action.builder.exception.AppContextException;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/2 17:06
 */
public class CreateEnvironmentStoreProvider implements ActionStoreProvider {

    private final AppEnvironmentService appEnvironmentService;

    public CreateEnvironmentStoreProvider(AppEnvironmentService appEnvironmentService) {
        this.appEnvironmentService = appEnvironmentService;
    }

    @Override
    public <T> void provider(T result) {
        AppEnvironment appEnvironment = (AppEnvironment) result;

        AppEnvironment history = appEnvironmentService.getOne(Wrappers.<AppEnvironment>query()
                .lambda()
                .eq(AppEnvironment::getClientName, appEnvironment.getClientName()));

        if (history != null) {
            throw new AppContextException("已经包含了 " + appEnvironment.getClientName() + " 的客户端名称");
        }

        appEnvironmentService.save(appEnvironment);
    }

    @Override
    public boolean supports(Class<?> rst) {
        return rst.isAssignableFrom(AppEnvironment.class);
    }
}
