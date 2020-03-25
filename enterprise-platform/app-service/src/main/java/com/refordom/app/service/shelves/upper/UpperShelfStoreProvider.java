package com.refordom.app.service.shelves.upper;

import com.omc.builder.ResultToken;
import com.omc.builder.api.StoreProvider;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.model.entity.AppDistro;

public class UpperShelfStoreProvider implements StoreProvider {

    private final AppDistroManagerService appDistroManagerService;

    public UpperShelfStoreProvider(AppDistroManagerService appDistroManagerService) {
        this.appDistroManagerService = appDistroManagerService;
    }

    @Override
    public void provider(ResultToken result) {
        AppDistro appDistro = (AppDistro) result;
        appDistro.setShelves(true);
        appDistroManagerService.updateDistroAppByAppId(appDistro);
    }

    @Override
    public boolean supports(Class<?> rst) {
        return AppDistro.class.isAssignableFrom(rst);
    }

}