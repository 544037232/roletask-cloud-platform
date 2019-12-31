package com.refordom.app.config.provisioning;

import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.model.AppInstallHistoryManagerService;
import com.refordom.app.model.entity.AppInstallHistory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public class AppInstallHistoryConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppInstallHistoryManagerService, AppInstallHistory, AppInstallHistoryConfigurer<B>, B> {

    public AppInstallHistoryConfigurer() {
        super(AppInstallHistoryManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppInstallHistoryManagerService(getManagerService());
    }

    @Override
    protected AppInstallHistoryManagerService initInMemoryManager() {
        return new AppInstallHistoryManagerInMemory();
    }

    @Override
    protected void createModel(AppInstallHistory detail) {
        getManagerService().createHistoryInstallApp(detail);
    }


    public static class AppInstallHistoryManagerInMemory implements AppInstallHistoryManagerService {

        private Map<String, AppInstallHistory> appInstallHistoryMap = new ConcurrentHashMap<>();

        @Override
        public AppInstallHistory getHistoryInstallApp(String appId, String clientId) {
            return appInstallHistoryMap.get(getKey(appId, clientId));
        }

        @Override
        public void createHistoryInstallApp(AppInstallHistory appInstallHistory) {
            appInstallHistoryMap.put(getKey(appInstallHistory.getAppId(), appInstallHistory.getClientId()), appInstallHistory);
        }

        private String getKey(String appId, String clientId) {
            return appId.concat(clientId);
        }
    }

}
