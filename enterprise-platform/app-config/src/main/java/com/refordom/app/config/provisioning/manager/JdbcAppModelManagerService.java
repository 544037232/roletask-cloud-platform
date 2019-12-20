package com.refordom.app.config.provisioning.manager;

import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.model.AppModel;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.app.model.AppModelService;
import com.refordom.app.model.enums.AppEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/20 10:42
 */
public class JdbcAppModelManagerService implements AppModelManagerService {

    private final Map<AppEnum, AppModelService> appModelServices;

    public JdbcAppModelManagerService(Map<AppEnum, AppModelService> appModelServices) {
        this.appModelServices = new HashMap<>(appModelServices);
    }

    @Override
    public AppModel getModel(String appId, AppEnum appType) {

        AppModel appModel = appModelServices.get(appType).getModel(appId);

        if (appModel == null) {
            throw new DeprecatedException("");
        }
        return appModel;
    }

    @Override
    public void createModel(AppModel appModel, AppEnum appType) {
        this.appModelServices.get(appType).createModel(appModel);
    }

}
