package com.refordom.app.service.shelves.lower;

import com.refordom.app.config.AppStoreProvider;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.model.entity.AppDistro;

/**
 * @author pricess.wang
 * @date 2020/1/9 11:08
 */
public class LowerShelfStoreProvider implements AppStoreProvider {

    private final AppManager appManager;

    public LowerShelfStoreProvider(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public <T> void provider(T result) {
        AppDistro appDistro = (AppDistro) result;
        appDistro.setShelves(false);
        appManager.getAppDistroManagerService().updateDistroAppByAppId(appDistro);
    }

    @Override
    public boolean supports(Class<?> rst) {
        return AppDistro.class.isAssignableFrom(rst);
    }

}
