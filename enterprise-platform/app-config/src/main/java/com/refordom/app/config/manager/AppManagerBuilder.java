package com.refordom.app.config.manager;

import com.refordom.app.config.AppModelManager;
import com.refordom.app.config.provisioning.*;
import com.refordom.app.model.*;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.context.ApplicationContext;

/**
 * @author pricess.wang
 * @date 2019/12/26 15:24
 */
public class AppManagerBuilder extends AbstractConfiguredObjectBuilder<AppManager, AppManagerBuilder>
        implements ModelManagerBuilder<AppManagerBuilder> {

    private ApplicationContext context;

    private AppDetailsManagerService appDetailsManagerService;

    private AppDistroManagerService appDistroManagerService;

    private AppHistoryManagerService appHistoryManagerService;

    private AppRunningManagerService appRunningManagerService;

    private AppInstallHistoryManagerService appInstallHistoryManagerService;

    private AppMadeDistroManagerService appMadeDistroManagerService;

    public AppManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor, ApplicationContext context) {
        super(objectPostProcessor);
        this.context = context;
    }

    @Override
    protected AppManager performBuild() throws Exception {
        return new AppModelManager(
                appDetailsManagerService,
                appDistroManagerService,
                appHistoryManagerService,
                appRunningManagerService,
                appInstallHistoryManagerService,
                appMadeDistroManagerService);
    }

    /**
     * 发行版本配置
     */
    public AppDetailConfigurer<AppManagerBuilder> appDetail() throws Exception {
        return getOrApply(new AppDetailConfigurer<>());
    }

    /**
     * 发行版本配置
     */
    public AppDistroConfigurer<AppManagerBuilder> distro() throws Exception {
        return getOrApply(new AppDistroConfigurer<>());
    }

    /**
     * 各客户环境运行中的版本
     */
    public AppRunningConfigurer<AppManagerBuilder> running() throws Exception {
        return getOrApply(new AppRunningConfigurer<>());
    }

    /**
     * 应用发布和的历史
     */
    public AppHistoryConfigurer<AppManagerBuilder> history() throws Exception {
        return getOrApply(new AppHistoryConfigurer<>());
    }

    /**
     * 客户环境安装历史
     */
    public AppInstallHistoryConfigurer<AppManagerBuilder> installHistory() throws Exception {
        return getOrApply(new AppInstallHistoryConfigurer<>());
    }

    /**
     * 客户环境二次开发发布版本
     */
    public AppMadeDistroConfigurer<AppManagerBuilder> madeDistro() throws Exception {
        return getOrApply(new AppMadeDistroConfigurer<>());
    }

    @Override
    public AppManagerBuilder setAppDetailsManagerService(AppDetailsManagerService appDetailsManagerService) {
        this.appDetailsManagerService = appDetailsManagerService;
        return this;
    }

    @Override
    public AppManagerBuilder setAppDistroManagerService(AppDistroManagerService appDistroManagerService) {
        this.appDistroManagerService = appDistroManagerService;
        return this;
    }

    @Override
    public AppManagerBuilder setAppHistoryManagerService(AppHistoryManagerService appHistoryManagerService) {
        this.appHistoryManagerService = appHistoryManagerService;
        return this;
    }

    @Override
    public AppManagerBuilder setAppRunningManagerService(AppRunningManagerService appRunningManagerService) {
        this.appRunningManagerService = appRunningManagerService;
        return this;
    }

    @Override
    public AppManagerBuilder setAppInstallHistoryManagerService(AppInstallHistoryManagerService appInstallHistoryManagerService) {
        this.appInstallHistoryManagerService = appInstallHistoryManagerService;
        return this;
    }

    @Override
    public AppManagerBuilder setAppMadeDistroManagerService(AppMadeDistroManagerService appMadeDistroManagerService) {
        this.appMadeDistroManagerService = appMadeDistroManagerService;
        return this;
    }

    @Override
    public ApplicationContext getContext() {
        return context;
    }

}
