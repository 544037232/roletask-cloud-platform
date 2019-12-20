package com.refordom.app.config.provisioning.configuration;

import com.refordom.app.config.provisioning.builders.AppModelBuilder;
import com.refordom.app.config.provisioning.configurers.AppModelConfigurer;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.app.model.enums.AppEnum;
import com.refordom.app.model.service.AppAccountService;
import com.refordom.app.model.service.AppBusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
public class AppManagerConfiguration {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    @SuppressWarnings("rawtypes,unchecked")
    public AppModelManagerService clientDetailsService(ApplicationContext context) throws Exception {
        AppModelConfigurer configurer = new AppModelConfigurer(context, new AppModelBuilder());

        configurer
                .jdbc()
                .registerService(AppEnum.ACCOUNT, AppAccountService.class)
                .registerService(AppEnum.BUSINESS, AppBusinessService.class);
        return configurer.and().build();
    }

}
