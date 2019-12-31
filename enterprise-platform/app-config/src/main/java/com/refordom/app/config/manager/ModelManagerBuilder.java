package com.refordom.app.config.manager;

import com.refordom.app.model.*;
import com.refordom.common.builder.ObjectBuilder;
import org.springframework.context.ApplicationContext;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:17
 */
public interface ModelManagerBuilder<P extends ModelManagerBuilder<P>> extends ObjectBuilder<AppManager> {

    P setAppDetailsManagerService(AppDetailsManagerService appDetailsManagerService);

    P setAppDistroManagerService(AppDistroManagerService appDistroManagerService);

    P setAppHistoryManagerService(AppHistoryManagerService appHistoryManagerService);

    P setAppRunningManagerService(AppRunningManagerService appRunningManagerService);

    P setAppInstallHistoryManagerService(AppInstallHistoryManagerService appInstallHistoryManagerService);

    P setAppMadeDistroManagerService(AppMadeDistroManagerService appMadeDistroManagerService);
    ApplicationContext getContext();
}
