package com.refordom.app.config.provisioning.builders;

import com.refordom.app.config.provisioning.manager.JdbcAppModelManagerService;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.app.model.AppModelService;
import com.refordom.app.model.enums.AppEnum;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JdbcAppModelBuilder extends AppModelBuilder<JdbcAppModelBuilder> {

    private ApplicationContext context;

    private Map<AppEnum, AppModelService> appModelServices = new HashMap<>();

    public JdbcAppModelBuilder(ApplicationContext context) {
        this.context = context;
    }

    public JdbcAppModelBuilder registerService(AppEnum appType, AppModelService service) {
        this.appModelServices.put(appType, service);
        return this;
    }

    public JdbcAppModelBuilder registerService(AppEnum appType, Class<?> clazz) {
        this.appModelServices.put(appType, (AppModelService) context.getBean(clazz));
        return this;
    }

    @Override
    protected AppModelManagerService performBuild() {
        return new JdbcAppModelManagerService(appModelServices);
    }

}
 