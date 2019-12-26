package com.refordom.app.config.manager;

import com.refordom.app.config.AppProvider;
import com.refordom.app.config.AppProviderManager;
import com.refordom.app.config.provisioning.configurer.AppInMemoryConfigurer;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/26 15:24
 */
public class AppManagerBuilder extends AbstractConfiguredObjectBuilder<AppManager, AppManagerBuilder>
        implements AppProviderManagerBuilder<AppManagerBuilder> {

    private List<AppProvider> providers = new ArrayList<>();

    private ApplicationContext context;

    public AppManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor, ApplicationContext context) {
        super(objectPostProcessor);
        this.context = context;
    }

    @Override
    protected AppManager performBuild() throws Exception {
        return new AppProviderManager();
    }

    public AppInMemoryConfigurer<AppManagerBuilder> inMemory() throws Exception {
        return getOrApply(new AppInMemoryConfigurer<>());
    }

    @Override
    public AppManagerBuilder appProvider(AppProvider appProvider) {
        this.providers.add(appProvider);
        return this;
    }
}
