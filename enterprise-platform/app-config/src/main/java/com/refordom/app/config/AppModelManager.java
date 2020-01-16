package com.refordom.app.config;

import com.refordom.app.config.manager.AppManager;
import com.refordom.app.model.*;

/**
 * 应用接口管理器，维护应用处理的各个业务接口
 *
 * @author pricess.wang
 * @date 2019/12/26 19:12
 */
public class AppModelManager implements AppManager {

    private final AppDetailsManagerService appDetailsManagerService;

    private final AppDistroManagerService appDistroManagerService;

    private final AppHistoryManagerService appHistoryManagerService;

    private final AppRunningManagerService appRunningManagerService;

    private final AppInstallHistoryManagerService appInstallHistoryManagerService;

    private final AppMadeDistroManagerService appMadeDistroManagerService;

    public AppModelManager(AppDetailsManagerService appDetailsManagerService,
                           AppDistroManagerService appDistroManagerService,
                           AppHistoryManagerService appHistoryManagerService,
                           AppRunningManagerService appRunningManagerService,
                           AppInstallHistoryManagerService appInstallHistoryManagerService,
                           AppMadeDistroManagerService appMadeDistroManagerService) {

        this.appDetailsManagerService = appDetailsManagerService;
        this.appDistroManagerService = appDistroManagerService;
        this.appHistoryManagerService = appHistoryManagerService;
        this.appRunningManagerService = appRunningManagerService;
        this.appInstallHistoryManagerService = appInstallHistoryManagerService;
        this.appMadeDistroManagerService = appMadeDistroManagerService;
    }

    @Override
    public AppDistroManagerService getAppDistroManagerService() {
        return appDistroManagerService;
    }

    @Override
    public AppHistoryManagerService getAppHistoryManagerService() {
        return appHistoryManagerService;
    }

    @Override
    public AppRunningManagerService getAppRunningManagerService() {
        return appRunningManagerService;
    }

    @Override
    public AppDetailsManagerService getAppDetailsManagerService() {
        return appDetailsManagerService;
    }

    @Override
    public AppInstallHistoryManagerService getAppInstallHistoryManagerService() {
        return appInstallHistoryManagerService;
    }

    @Override
    public AppMadeDistroManagerService getAppMadeDistroManagerService() {
        return appMadeDistroManagerService;
    }
}
