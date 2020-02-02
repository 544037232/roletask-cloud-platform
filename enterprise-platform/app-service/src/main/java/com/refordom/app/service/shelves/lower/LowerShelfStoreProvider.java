package com.refordom.app.service.shelves.lower;

import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.common.action.builder.ActionStoreProvider;

/**
 * @author pricess.wang
 * @date 2020/1/9 11:08
 */
public class LowerShelfStoreProvider implements ActionStoreProvider {

    private final AppDistroManagerService appDistroManagerService;

    public LowerShelfStoreProvider(AppDistroManagerService appDistroManagerService) {
        this.appDistroManagerService = appDistroManagerService;
    }

    @Override
    public <T> void provider(T result) {
        AppDistro appDistro = (AppDistro) result;
        appDistro.setShelves(false);
        appDistroManagerService.updateDistroAppByAppId(appDistro);
    }

    @Override
    public boolean supports(Class<?> rst) {
        return AppDistro.class.isAssignableFrom(rst);
    }

}
