package com.refordom.app.service.client;

import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.ActionStoreProvider;
import com.refordom.common.action.builder.exception.AppContextException;
import org.springframework.dao.DuplicateKeyException;

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

        try {

            appEnvironmentService.save(appEnvironment);
        } catch (DuplicateKeyException e) {
            throw new AppContextException("已经包含了 " + appEnvironment.getClientName() + " 的客户端名称");
        }
    }

    @Override
    public boolean supports(Class<?> rst) {
        return rst.isAssignableFrom(AppEnvironment.class);
    }
}
