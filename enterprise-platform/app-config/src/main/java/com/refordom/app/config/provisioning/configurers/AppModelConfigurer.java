package com.refordom.app.config.provisioning.configurers;

import com.refordom.app.config.provisioning.builders.AppModelBuilder;
import com.refordom.app.config.provisioning.builders.InMemoryAppModelBuilder;
import com.refordom.app.config.provisioning.builders.JdbcAppModelBuilder;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.common.builder.ObjectConfigurerAdapter;
import org.springframework.context.ApplicationContext;

public class AppModelConfigurer extends
        ObjectConfigurerAdapter<AppModelManagerService, AppModelBuilder<?>> {

    private ApplicationContext context;

    public <B extends AppModelBuilder<B>> AppModelConfigurer(ApplicationContext context, AppModelBuilder<B> builder) {
        setBuilder(builder);
        this.context = context;
    }

    public AppModelBuilder<?> withAppModelService(AppModelManagerService appModelManagerService) throws Exception {
        setBuilder(getBuilder().clients(appModelManagerService));
        return this.and();
    }

    public InMemoryAppModelBuilder inMemory() throws Exception {
        InMemoryAppModelBuilder next = getBuilder().inMemory();
        setBuilder(next);
        return next;
    }

    public JdbcAppModelBuilder jdbc() throws Exception {
        JdbcAppModelBuilder next = getBuilder().jdbc(context);
        setBuilder(next);
        return next;
    }

    @Override
    public void init(AppModelBuilder<?> builder) throws Exception {
    }

    @Override
    public void configure(AppModelBuilder<?> builder) throws Exception {
    }

}
