package com.refordom.app.config.provisioning;

import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.model.AppRunningManagerService;
import com.refordom.app.model.entity.AppRunning;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public class AppRunningConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppRunningManagerService, AppRunning, AppRunningConfigurer<B>, B> {

    public AppRunningConfigurer() {
        super(AppRunningManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppRunningManagerService(getManagerService());
    }

    @Override
    protected AppRunningManagerService initInMemoryManager() {
        return new AppRunningManagerInMemory();
    }

    @Override
    protected void createModel(AppRunning detail) {
        getManagerService().createRunningApp(detail);
    }

    public static class AppRunningManagerInMemory implements AppRunningManagerService {

        private Map<String, AppRunning> appRunningMap = new ConcurrentHashMap<>();

        @Override
        public AppRunning getRunningApp(String appId, String clientId) {
            return appRunningMap.get(getKey(appId, clientId));
        }

        @Override
        public void deleteRunningApp(String appId, String clientId) {
            appRunningMap.remove(getKey(appId, clientId));
        }

        @Override
        public void createRunningApp(AppRunning appRunning) {
            appRunningMap.put(getKey(appRunning.getAppId(), appRunning.getClientId()), appRunning);
        }

        private String getKey(String appId, String clientId) {
            return appId.concat(clientId);
        }
    }

}
