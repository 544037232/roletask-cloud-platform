package com.refordom.app.config.provisioning.manager;

import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.model.AppModel;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.app.model.enums.AppEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/20 10:42
 */
public class InMemoryAppModelManagerService implements AppModelManagerService {

    private Map<String, AppModel> appModelStore = new HashMap<>();

    @Override
    public AppModel getModel(String appId, AppEnum appType) {
        AppModel appModel = appModelStore.get(appId);

        if (appModel == null) {
            throw new DeprecatedException("");
        }
        return appModel;
    }

    @Override
    public void createModel(AppModel appModel, AppEnum appType) {
        this.appModelStore.put(appModel.getAppId(), appModel);
    }

    public void setAppModelStore(Map<String, AppModel> clientDetails) {
        this.appModelStore = new HashMap<>(clientDetails);
    }
}
