package com.refordom.app.config.provisioning;

import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.model.AppMadeDistroManagerService;
import com.refordom.app.model.entity.AppMadeDistro;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public class AppMadeDistroConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppMadeDistroManagerService, AppMadeDistro, AppMadeDistroConfigurer<B>, B> {

    public AppMadeDistroConfigurer() {
        super(AppMadeDistroManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppMadeDistroManagerService(getManagerService());
    }

    @Override
    protected AppMadeDistroManagerService initInMemoryManager() {
        return new AppMadeDistroManagerInMemory();
    }

    @Override
    protected void createModel(AppMadeDistro appMadeDistro) {
        getManagerService().createMadeDistroApp(appMadeDistro);
    }

    public static class AppMadeDistroManagerInMemory implements AppMadeDistroManagerService {

        private Map<String, AppMadeDistro> appMadeDistroMap = new ConcurrentHashMap<>();

        @Override
        public AppMadeDistro getMadeDistroApp(String appId, String clientId) {
            return appMadeDistroMap.get(getKey(appId, clientId));
        }

        @Override
        public void updateMadeDistroAppByAppId(AppMadeDistro appMadeDistro) {
            if (appMadeDistroMap.get(getKey(appMadeDistro.getAppId(), appMadeDistro.getClientId())) == null) {
                throw new DeprecatedException("update distro entity is null");
            }
            appMadeDistroMap.put(getKey(appMadeDistro.getAppId(), appMadeDistro.getClientId()), appMadeDistro);
        }

        @Override
        public void createMadeDistroApp(AppMadeDistro appMadeDistro) {
            appMadeDistroMap.put(getKey(appMadeDistro.getAppId(), appMadeDistro.getClientId()), appMadeDistro);
        }

        private String getKey(String appId, String clientId) {
            return appId.concat(clientId);
        }
    }

}
