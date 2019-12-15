package com.refordom.app.config;

import com.refordom.app.config.core.AppRequest;
import com.refordom.common.builder.ObjectConfigurer;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.Filter;
import java.util.List;

/**
 * 应用顶级配置入口
 *
 * @author pricess.wang
 * @date 2019/12/13 18:20
 */
@ComponentScan("com.refordom.app.config")
public class AppConfiguration {

    private AppRequest appRequest;

    private List<ObjectConfigurer<Filter, AppRequest>> requestAppStoreConfigurers;

    @Bean
    public Filter appFilterChain(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        boolean hasConfigurers = requestAppStoreConfigurers != null
                && !requestAppStoreConfigurers.isEmpty();

        if (!hasConfigurers) {
            AppRequestConfigurerAdapter adapter = objectPostProcessor
                    .postProcess(new AppRequestConfigurerAdapter() {
                    });
            appRequest.apply(adapter);
        }
        return appRequest.build();
    }

    @Bean
    public FilterRegistrationBean<Filter> securityFilterChain(
            @Qualifier(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME)
                    Filter securityFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(securityFilter);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter> appFilterChain(@Qualifier("appFilterChain") Filter appFilterChain) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(appFilterChain);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }

    @Autowired(required = false)
    public void setFilterChainProxyConfigurer(
            ObjectPostProcessor<Object> objectPostProcessor,
            @Value("#{@autowiredAppRequestConfigurersIgnoreParents.getAppRequestConfigurers()}") List<ObjectConfigurer<Filter, AppRequest>> requestConfigurers)
            throws Exception {
        appRequest = objectPostProcessor
                .postProcess(new AppRequest(objectPostProcessor));

        for (ObjectConfigurer<Filter, AppRequest> objectConfigurer : requestConfigurers) {
            appRequest.apply(objectConfigurer);
        }
        this.requestAppStoreConfigurers = requestConfigurers;
    }

    @Bean
    public static AutowiredAppRequestConfigurersIgnoreParents autowiredAppRequestConfigurersIgnoreParents(
            ConfigurableListableBeanFactory beanFactory) {
        return new AutowiredAppRequestConfigurersIgnoreParents(beanFactory);
    }
}
