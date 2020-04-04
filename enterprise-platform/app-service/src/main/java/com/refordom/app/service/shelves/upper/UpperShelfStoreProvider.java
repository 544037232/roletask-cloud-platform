package com.refordom.app.service.shelves.upper;

import com.omc.builder.ResultToken;
import com.omc.builder.api.StoreProvider;
import com.refordom.app.model.AppDistroManagerService;

public class UpperShelfStoreProvider implements StoreProvider {

    private final AppDistroManagerService appDistroManagerService;

    public UpperShelfStoreProvider(AppDistroManagerService appDistroManagerService) {
        this.appDistroManagerService = appDistroManagerService;
    }

    @Override
    public void provider(ResultToken result) {
        UpperShelfResultToken appDistro = (UpperShelfResultToken) result;
        appDistro.getAppDistro().setShelves(true);
        appDistroManagerService.updateDistroAppByAppId(appDistro.getAppDistro());

    }

}