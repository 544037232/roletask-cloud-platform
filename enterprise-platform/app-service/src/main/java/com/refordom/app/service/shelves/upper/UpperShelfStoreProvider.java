package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.AppStoreProvider;
import com.refordom.app.config.AppToken;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.model.entity.AppDistro;

/**
 * @author pricess.wang
 * @date 2020/1/9 11:08
 */
public class UpperShelfStoreProvider implements AppStoreProvider {

    private final AppManager appManager;

    public UpperShelfStoreProvider(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public AppToken provider(AppToken appToken) {
        AppDistro appDistro = (AppDistro) appToken;
        appDistro.setShelves(true);
        appManager.getAppDistroManagerService().updateDistroAppByAppId(appDistro);
        return appToken;
    }

    @Override
    public boolean supports(Class<?> context) {
        return true;
    }
}
