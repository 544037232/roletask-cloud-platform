package com.refordom.app.service.client;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.exception.ConcurrentException;
import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.ActionStoreProvider;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/2 17:06
 */
public class UpdateEnvironmentStoreProvider implements ActionStoreProvider {

    private final AppEnvironmentService appEnvironmentService;

    public UpdateEnvironmentStoreProvider(AppEnvironmentService appEnvironmentService) {
        this.appEnvironmentService = appEnvironmentService;
    }

    @Override
    public <T> void provider(T result) {
        AppEnvironment appEnvironment = (AppEnvironment) result;

        AppEnvironment update = appEnvironmentService
                .getOne(
                        Wrappers.<AppEnvironment>query()
                                .lambda()
                                .eq(AppEnvironment::getClientId, appEnvironment.getClientId())
                );

        if (update == null) {
            throw new ConcurrentException("修改的客户端不存在");
        }

        appEnvironment.setId(update.getId());

        appEnvironmentService.updateById(appEnvironment);
    }

    @Override
    public boolean supports(Class<?> rst) {
        return rst.isAssignableFrom(AppEnvironment.class);
    }
}
