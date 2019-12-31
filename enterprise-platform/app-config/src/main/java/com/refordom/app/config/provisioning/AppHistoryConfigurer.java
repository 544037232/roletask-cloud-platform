package com.refordom.app.config.provisioning;

import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.app.model.AppHistoryManagerService;
import com.refordom.app.model.entity.AppHistory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:14
 */
public class AppHistoryConfigurer<B extends ModelManagerBuilder<B>> extends
        AbstractAppConfigurer<AppHistoryManagerService, AppHistory, AppHistoryConfigurer<B>, B> {

    public AppHistoryConfigurer() {
        super(AppHistoryManagerService.class);
    }

    @Override
    public void init(B builder) throws Exception {
        if (getManagerService() == null) {
            inMemory();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.setAppHistoryManagerService(getManagerService());
    }

    @Override
    protected AppHistoryManagerService initInMemoryManager() {
        return new AppHistoryManagerInMemory();
    }

    @Override
    protected void createModel(AppHistory detail) {
        getManagerService().createHistoryApp(detail);
    }


    public static class AppHistoryManagerInMemory implements AppHistoryManagerService {

        private Map<String, AppHistory> appHistoryMap = new ConcurrentHashMap<>();

        @Override
        public AppHistory getHistoryApp(String appId) {
            return appHistoryMap.get(appId);
        }

        @Override
        public void createHistoryApp(AppHistory appHistory) {
            appHistoryMap.put(appHistory.getAppId(), appHistory);
        }

    }

}
