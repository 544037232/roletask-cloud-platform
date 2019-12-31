package com.refordom.app.config.provisioning;

import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.core.AppDetails;
import com.refordom.app.model.AppDetailsManagerService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public final class AppDetailConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppDetailsManagerService, AppDetails, AppDetailConfigurer<B>, B> {

    public AppDetailConfigurer() {
        super(AppDetailsManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppDetailsManagerService(getManagerService());
    }

    @Override
    protected AppDetailsManagerService initInMemoryManager() {
        return new AppDetailsManagerInMemory();
    }

    @Override
    protected void createModel(AppDetails detail) {
        getManagerService().createApp(detail);
    }

    public static class AppDetailsManagerInMemory implements AppDetailsManagerService {

        private Map<String, AppDetails> appDetailsMap = new ConcurrentHashMap<>();

        @Override
        public AppDetails getApp(String appId) {
            return appDetailsMap.get(appId);
        }

        @Override
        public void createApp(AppDetails appDetails) {
            appDetailsMap.put(appDetails.getAppId(), appDetails);
        }
    }
}
