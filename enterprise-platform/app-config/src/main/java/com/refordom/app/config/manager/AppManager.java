package com.refordom.app.config.manager;


import com.refordom.app.model.*;

/**
 * @author pricess.wang
 * @date 2019/12/26 15:23
 */
public interface AppManager {

    AppDistroManagerService getAppDistroManagerService();

    AppHistoryManagerService getAppHistoryManagerService();

    AppRunningManagerService getAppRunningManagerService();

    AppDetailsManagerService getAppDetailsManagerService();

    AppInstallHistoryManagerService getAppInstallHistoryManagerService();

    AppMadeDistroManagerService getAppMadeDistroManagerService();
}
