package com.refordom.app.config.provisioning;

import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.model.entity.AppDistro;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public class AppDistroConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppDistroManagerService, AppDistro, AppDistroConfigurer<B>, B> {

    public AppDistroConfigurer() {
        super(AppDistroManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppDistroManagerService(getManagerService());
    }

    @Override
    protected AppDistroManagerService initInMemoryManager() {
        return new AppDistroManagerInMemory();
    }

    @Override
    protected void createModel(AppDistro detail) {
        getManagerService().createDistroApp(detail);
    }

    public static class AppDistroManagerInMemory implements AppDistroManagerService {

        private Map<String, AppDistro> appDistroMap = new ConcurrentHashMap<>();

        @Override
        public AppDistro getDistroApp(String appId) {
            return appDistroMap.get(appId);
        }

        @Override
        public void updateDistroAppByAppId(AppDistro appDistro) {
            if (appDistroMap.get(appDistro.getAppId()) == null) {
                throw new DeprecatedException("update distro entity is null");
            }
            appDistroMap.put(appDistro.getAppId(), appDistro);
        }

        @Override
        public void createDistroApp(AppDistro appDistro) {
            appDistroMap.put(appDistro.getAppId(), appDistro);
        }

    }

}
